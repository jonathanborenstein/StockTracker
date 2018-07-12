package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class StockTransaction {

	@Id
	@GeneratedValue
	private Long id;

	@Digits(integer = 8, fraction = 0)
	private int numOfShares;
	
	@Digits(integer = 8, fraction = 10)
	private int sharesInLot;
	
	private State state;

	public static enum State {
		PURCHASE, SALE
	}
	

	@Column(precision = 11, scale = 3)
	private BigDecimal priceOfShares;

	@NotBlank
	private String symbol;

	@Column(name = "added")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date added;


	@PrePersist
	protected void onCreate() {
		if (added == null) {
			added = new Date();
		}
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}


	public StockTransaction() {}

	public StockTransaction(int numOfShares, int sharesInLot, BigDecimal priceOfShares, String symbol) {
		this.numOfShares = numOfShares;
		this.sharesInLot = sharesInLot;
		this.priceOfShares = priceOfShares;
		this.symbol = symbol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}

	public int getSharesInLot() {
		return sharesInLot;
	}

	public void setSharesInLot(int sharesInLot) {
		this.sharesInLot = sharesInLot;
	}

	public BigDecimal getPriceOfShares() {
		return priceOfShares;
	}

	public void setPriceOfShares(BigDecimal priceOfShares) {
		this.priceOfShares = priceOfShares;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


}
