package com.example.demo;

import java.math.BigDecimal;

public class StockTotalObject {
	
	private BigDecimal totalPrice;
	private BigDecimal averagePrice;
	private BigDecimal profit;
	private BigDecimal currentPrice;
	private int totalShares;
	private String symbol;
	
	
	public StockTotalObject(BigDecimal totalPrice, BigDecimal averagePrice, BigDecimal profit, BigDecimal currentPrice,
			int totalShares, String symbol) {
		this.totalPrice = totalPrice;
		this.averagePrice = averagePrice;
		this.profit = profit;
		this.currentPrice = currentPrice;
		this.totalShares = totalShares;
		this.symbol = symbol;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getTotalShares() {
		return totalShares;
	}
	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	

}
