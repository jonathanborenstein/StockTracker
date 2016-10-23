package com.example;

import java.util.ArrayList;
import java.util.List;

public class StockWrapper {
	
	private List<Stock> stockList;
	
	public StockWrapper(){
		this.stockList = new ArrayList<Stock>();
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	
	public void add(Stock stock){
		this.stockList.add(stock);
	}
	
	

}
