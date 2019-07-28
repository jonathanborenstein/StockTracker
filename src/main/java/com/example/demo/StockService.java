package com.example.demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class StockService {
	
	@Autowired
	private StockTransactionRepository stockTransactionRepository;
	
	@Autowired
	private RealizedStockRepository realizedStockRepository;
	
	
	public List<StockTotalObject> getStockInfo(List<StockTransaction> stockList) {
		
		BigDecimal totalPrice;
		BigDecimal averagePrice;
		BigDecimal profit;
		BigDecimal currentPrice;
		int totalShares;
		String symbol;
		List <StockTotalObject> stockTotal = new ArrayList<StockTotalObject>();
		Set<String> stockSet = new HashSet<String>();


		for(int i = 0; i < stockList.size(); i++){

			if (!stockSet.contains(stockList.get(i).getSymbol())){
				stockSet.add(stockList.get(i).getSymbol());
				totalShares = getTotalShares(stockList.get(i));

				if (totalShares > 0){
					totalPrice = getTotalPrice(stockList.get(i)); 
					averagePrice = getAveragePrice(totalPrice, totalShares);
					profit = getUnrealizedProfit(stockList.get(i), totalPrice, totalShares).setScale(3, RoundingMode.HALF_UP);
					symbol = stockList.get(i).getSymbol();
					currentPrice = getCurrentPrice(symbol);

					stockTotal.add(new StockTotalObject(totalPrice, averagePrice, profit, 
							currentPrice, totalShares, symbol));
					
				}
			}
		}

		stockSet.clear();
		return stockTotal;

	}
	
	public int getTotalShares(StockTransaction stock){
		
		int totalShares;

		List<StockTransaction> stockList = stockTransactionRepository.findBySymbol(stock.getSymbol());

		totalShares = stockList
				.stream()
				.filter(e -> e.getSharesInLot() > 0)
				.mapToInt(e -> e.getSharesInLot())
				.sum();

		return totalShares;

	}
	
	public BigDecimal getTotalPrice(StockTransaction stock){
		
		Function<StockTransaction, BigDecimal> stockMapper = s-> s.getPriceOfShares()
				.multiply(new BigDecimal(s.getSharesInLot()));
		
		BigDecimal totalPrice;

		List<StockTransaction> stockList = stockTransactionRepository.findBySymbol(stock.getSymbol());

		totalPrice = stockList
				.stream()
				.filter(e -> e.getSharesInLot() > 0)
				.map(stockMapper)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		

		return totalPrice.setScale(3, RoundingMode.HALF_UP);

	}
	
	public BigDecimal getAveragePrice(BigDecimal totalPrice, int totalShares){
		return totalPrice.divide(new BigDecimal(totalShares), 3, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getMarketValue(StockTransaction stock, int totalShares){
		return getCurrentPrice(stock.getSymbol()).multiply(new BigDecimal(totalShares));
	}

	public BigDecimal getUnrealizedProfit(StockTransaction stock, BigDecimal totalPrice, int totalShares){
		return getMarketValue(stock, totalShares).subtract(totalPrice);
	}
	
	public String getTotalProfit(List<StockTotalObject> stockTotalList){

		BigDecimal profit = stockTotalList
				.stream()
				.map(e -> e.getProfit())
		        .reduce(BigDecimal.ZERO, BigDecimal::add);


		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		return formatter.format(profit);
	}
	
	public BigDecimal getCurrentPrice(String symbol){
		
		JsonParser parser;
		JsonElement element;
		JsonObject dataset;
		String url;
		String json = "";
		
		BigDecimal price;

		url = "https://financialmodelingprep.com/api/v3/stock/real-time-price/"+ symbol;

		try {
			json = IOUtils.toString(new URL(url), "UTF-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			return price = BigDecimal.ZERO;
		}
		parser = new JsonParser();
		element = parser.parse(json);
		dataset = element.getAsJsonObject();
		price = BigDecimal.valueOf(dataset.get("price").getAsDouble());

		return price.setScale(3, RoundingMode.HALF_UP);
	}
	
	public List<StockRealized> calcRealizedProfit(StockTransaction stock) {
		

		List<StockTransaction> stockPrice = stockTransactionRepository.findBySymbol(stock.getSymbol());
		int shares = stock.getNumOfShares();//*  -1;
		BigDecimal proceeds = BigDecimal.ZERO;
		BigDecimal cost = BigDecimal.ZERO;
		BigDecimal realized = BigDecimal.ZERO;

		for (int i =0; i < stockPrice.size(); i++) {

			StockTransaction currentStock = stockPrice.get(i);

			if(shares <= currentStock.getSharesInLot() && currentStock.getSharesInLot() > 0){

				currentStock.setSharesInLot(currentStock.getSharesInLot() - shares);
				stockTransactionRepository.save(currentStock);

				proceeds = stock.getPriceOfShares().multiply(new BigDecimal(shares));
				cost = currentStock.getPriceOfShares().multiply(new BigDecimal(shares));
				realized = proceeds.subtract(cost);
				realizedStockRepository.save(new StockRealized(shares, stock.getPriceOfShares(), 
						proceeds, cost, stock.getSymbol(), realized));

				break;

			} else if (shares > currentStock.getSharesInLot() && currentStock.getSharesInLot() > 0){


				cost = currentStock.getPriceOfShares().multiply(new BigDecimal(currentStock.getSharesInLot()));
				proceeds = stock.getPriceOfShares().multiply(new BigDecimal(currentStock.getSharesInLot()));
				realized = proceeds.subtract(cost);
				realizedStockRepository.save(new StockRealized(currentStock.getSharesInLot(), stock.getPriceOfShares(),
						proceeds, cost, stock.getSymbol(), realized));

				shares = shares - currentStock.getSharesInLot();
				currentStock.setSharesInLot(0);
				stockTransactionRepository.save(currentStock);
			}
		}
		return realizedStockRepository.findAll();
	}
	
	
	

}
