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

		List<Stock> stockList = new ArrayList<Stock>();
		stockList = stockDao.findBySymbol(stock.getSymbol());
		totalShares = 0;
		for(int i =0; i < stockList.size(); i++){
			if (stockList.get(i).getSharesInLot() > 0){
				totalShares = totalShares + stockList.get(i).getSharesInLot();
			}
		}
		
		return totalShares;

	}

	public double getTotalPrice(Stock stock){

		List<Stock> stockList = new ArrayList<Stock>();
		stockList = stockDao.findBySymbol(stock.getSymbol());
		totalPrice = 0;

		for(int i =0; i < stockList.size(); i++){

			if (stockList.get(i).getSharesInLot() > 0){
				totalPrice = totalPrice + (stockList.get(i).getPrice() * stockList.get(i).getSharesInLot());
			}
		}



		return totalPrice;

	}


	public double getAveragePrice(){

		return totalPrice / totalShares ;
	}

	public double getProfit(String symbol){
		return (getCurrentPrice(symbol) - getAveragePrice()) * totalShares;
	}

	public String getTotalProfit(List<StockTotalObject> stockTotalList){
		double profit = 0;

		for (int i =0; i <stockTotalList.size(); i++){
			profit = profit + stockTotalList.get(i).getProfit();
		}
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.format(profit);

		return 	formatter.format(profit);

	}


	public void calcRealizedProfit(Stock stock) {

		List<Stock> stockPrice = stockDao.findBySymbol(stock.getSymbol());
		double shares = stock.getNumShares() *  -1;
		double proceeds = 0;
		double cost = 0;
		String realized = "";

		NumberFormat formatter = NumberFormat.getCurrencyInstance();


		for (int i =0; i < stockPrice.size(); i++)
		{
			if(shares < stockPrice.get(i).getSharesInLot() && stockPrice.get(i).getSharesInLot() > 0){
				stockPrice.get(i).setSharesInLot(stockPrice.get(i).getSharesInLot() - shares);
				stockDao.save(stockPrice.get(i));

				proceeds = stock.getPrice() * shares;
				cost = stockPrice.get(i).getPrice() * shares;
				realized = formatter.format(proceeds - cost);
				realizedDao.save(new StockRealized(shares, stock.getPrice(), stock.getSymbol(), proceeds, cost, realized));

				break;

			}
			else if (shares > stockPrice.get(i).getSharesInLot() && stockPrice.get(i).getSharesInLot() > 0){


				cost = stockPrice.get(i).getPrice() * stockPrice.get(i).getSharesInLot();
				proceeds = stock.getPrice() * stockPrice.get(i).getSharesInLot();
				realized = formatter.format(proceeds - cost);
				realizedDao.save(new StockRealized(stockPrice.get(i).getSharesInLot(), stock.getPrice(), stock.getSymbol(), proceeds, cost, realized));

				shares = shares - stockPrice.get(i).getSharesInLot();
				stockPrice.get(i).setSharesInLot(0);
				stockDao.save(stockPrice.get(i));

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