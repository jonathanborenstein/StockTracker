package com.example;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "realized")
public class StockRealized {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private double numShares;
	
	private double price;
	
	private String symbol;
	
	private String realizedProfit;
	
	public StockRealized(){
		
	}

	public StockRealized(double numShares, double price, String symbol, String realizedProfit) {
		this.numShares = numShares;
		this.price = price;
		this.symbol = symbol;
		this.realizedProfit = realizedProfit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getNumShares() {
		return numShares;
	}

	public void setNumShares(double numShares) {
		this.numShares = numShares;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRealizedProfit() {
		return realizedProfit;
	}

	public void setRealizedProfit(String realizedProfit) {
		this.realizedProfit = realizedProfit;
	}


	

}
