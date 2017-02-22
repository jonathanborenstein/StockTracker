package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class StockService {

	private double totalPrice;
	private double totalShares;
	private double price;

	private String url;
	private String json;
	private JsonParser parser;
	private JsonElement element;
	private JsonObject dataset;

	@Autowired
	StockDao stockDao;

	@Autowired
	RealizedDao realizedDao;

	Set<String> stockSet = new HashSet<String>();

	public double getCurrentPrice(String symbol){

		url = "http://www.google.com/finance/option_chain?q=" + symbol + "&output=json";
		try {
			json = IOUtils.toString(new URL(url), "UTF-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		parser = new JsonParser();
		element = parser.parse(json);
		dataset = element.getAsJsonObject();
		price = dataset.get("underlying_price").getAsDouble();

		return price;
	}

	public List<StockTotalObject> getStockInfo(List<Stock> stockList) {
		double totalPrice;
		double averagePrice;
		double profit;
		double currentPrice;
		String symbol;
		List <StockTotalObject> stockTotal = new ArrayList<StockTotalObject>();


		for(int i = 0; i < stockList.size(); i++){

			if (!stockSet.contains(stockList.get(i).getSymbol())){
				stockSet.add(stockList.get(i).getSymbol());
				totalShares = getTotalShares(stockList.get(i));

				if (totalShares > 0){
					totalPrice = getTotalPrice(stockList.get(i));
					averagePrice = getAveragePrice();
					profit = getUnrealizedProfit(stockList.get(i));
					symbol = stockList.get(i).getSymbol();
					currentPrice = getCurrentPrice(symbol);
					stockTotal.add(new StockTotalObject(totalShares, totalPrice, averagePrice, profit, currentPrice, symbol));
				}

			}
		}

		stockSet.clear();
		return stockTotal;

	}

	public double getMarketValue(Stock stock){
		return getCurrentPrice(stock.getSymbol()) * totalShares;
	}

	public double getUnrealizedProfit(Stock stock){
		return getMarketValue(stock) - totalPrice;
	}

	public double getTotalShares(Stock stock){

		List<Stock> stockList = stockDao.findBySymbol(stock.getSymbol());

		totalShares = stockList
				.stream()
				.filter(e -> e.getSharesInLot() > 0)
				.mapToDouble(e -> e.getSharesInLot())
				.sum();

		return totalShares;

	}

	public double getTotalPrice(Stock stock){

		List<Stock> stockList = stockDao.findBySymbol(stock.getSymbol());

		totalPrice = stockList
				.stream()
				.filter(e -> e.getSharesInLot() > 0)
				.mapToDouble(e -> e.getPrice() * e.getSharesInLot())
				.sum();

		return totalPrice;

	}

	public double getAveragePrice(){

		return totalPrice / totalShares ;
	}

	public String getTotalProfit(List<StockTotalObject> stockTotalList){

		double profit = stockTotalList
				.stream()
				.mapToDouble(e -> e.getProfit())
				.sum();

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		return formatter.format(profit);
	}

	public void calcRealizedProfit(Stock stock) {

		List<Stock> stockPrice = stockDao.findBySymbol(stock.getSymbol());
		double shares = stock.getNumShares() *  -1;
		double proceeds = 0;
		double cost = 0;
		String realized = "";

		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		for (int i =0; i < stockPrice.size(); i++) {
			
			Stock currentStock = stockPrice.get(i);

			if(shares < currentStock.getSharesInLot() && currentStock.getSharesInLot() > 0){
				currentStock.setSharesInLot(currentStock.getSharesInLot() - shares);
				stockDao.save(currentStock);

				proceeds = stock.getPrice() * shares;
				cost = currentStock.getPrice() * shares;
				realized = formatter.format(proceeds - cost);
				realizedDao.save(new StockRealized(shares, stock.getPrice(), stock.getSymbol(), proceeds, cost, realized));

				break;
			} else if (shares > currentStock.getSharesInLot() && currentStock.getSharesInLot() > 0){

				cost = currentStock.getPrice() * currentStock.getSharesInLot();
				proceeds = stock.getPrice() * currentStock.getSharesInLot();
				realized = formatter.format(proceeds - cost);
				realizedDao.save(new StockRealized(currentStock.getSharesInLot(), stock.getPrice(), stock.getSymbol(), proceeds, cost, realized));

				shares = shares - currentStock.getSharesInLot();
				currentStock.setSharesInLot(0);
				stockDao.save(currentStock);
			}
		}
	}



	public List<StockRealized> getRealizedProfits(){
		return realizedDao.findAll();
	}

	public void save(Stock stock){
		stockDao.save(stock);
	}

	public List<Stock> findAll(){
		return stockDao.findAllByOrderBySymbolAsc();
	}

}