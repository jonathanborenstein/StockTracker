package com.example.demo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockRealized {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int numShares;
	
	@Column(precision = 11, scale = 3)
	private BigDecimal price;
	
	@Column(precision = 11, scale = 3)
	private BigDecimal proceeds;
	
	@Column(precision = 11, scale = 3)
	private BigDecimal cost;
	
	private String symbol;
	
	@Column(precision = 11, scale = 3)
	private BigDecimal realizedProfit;
	
	public StockRealized(){}

	public StockRealized(int numShares, BigDecimal price, BigDecimal proceeds, BigDecimal cost, String symbol,
			BigDecimal realizedProfit) {
		this.numShares = numShares;
		this.price = price;
		this.proceeds = proceeds;
		this.cost = cost;
		this.symbol = symbol;
		this.realizedProfit = realizedProfit;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumShares() {
		return numShares;
	}

	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getProceeds() {
		return proceeds;
	}

	public void setProceeds(BigDecimal proceeds) {
		this.proceeds = proceeds;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getRealizedProfit() {
		return realizedProfit;
	}

	public void setRealizedProfit(BigDecimal realizedProfit) {
		this.realizedProfit = realizedProfit;
	}
	
	
	
	
	
}