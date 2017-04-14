package com.example;

public class StockTotalObject {
	
	private int totalShares;
	private double totalPrice;
	private double averagePrice;
	private double profit;
	private double currentPrice;
	private String symbol;
	
	
	public StockTotalObject(int totalShares, double totalPrice, double averagePrice, double profit, double currentPrice, String symbol) {
		this.totalShares = totalShares;
		this.totalPrice = totalPrice;
		this.averagePrice = averagePrice;
		this.profit = profit;
		this.currentPrice = currentPrice;
		this.symbol = symbol;
	}

	
	public int getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public double getProfit() {
		return profit;
	}
	
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	@Override
	public String toString() {
		return "StockTotalObject [totalShares=" + totalShares + ", totalPrice=" + totalPrice + ", averagePrice="
				+ averagePrice + ", profit=" + profit + ", currentPrice=" + currentPrice + ", symbol=" + symbol + "]";
	}
	
	

	

}