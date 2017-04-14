package com.example;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private int numShares;
	
	private double price;
	
	private int sharesInLot;
	
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

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}
	

	public int getSharesInLot() {
		return sharesInLot;
	}

	public void setSharesInLot(int sharesInLot) {
		this.sharesInLot = sharesInLot;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", numShares=" + numShares + ", price=" + price + ", symbol=" + symbol + ", added="
				+ added + "]";
	}
	
	

}