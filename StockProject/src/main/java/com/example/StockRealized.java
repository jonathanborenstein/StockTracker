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
	
	private double proceeds;
	
	private double cost;

	private String symbol;
	
	private String realizedProfit;
	
	public StockRealized(){
		
	}

	public StockRealized(double numShares, double price, String symbol, double proceeds, double cost, String realizedProfit) {
		this.numShares = numShares;
		this.price = price;
		this.symbol = symbol;
		this.proceeds = proceeds;
		this.cost = cost;
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
	

	public double getProceeds() {
		return proceeds;
	}

	public void setProceeds(double proceeds) {
		this.proceeds = proceeds;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


	public String getRealizedProfit() {
		return realizedProfit;
	}

	public void setRealizedProfit(String realizedProfit) {
		this.realizedProfit = realizedProfit;
	}


	

}