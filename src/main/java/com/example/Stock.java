package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "stocks")
public class Stock {

				private static Logger LOG = LoggerFactory.getLogger("com.example.Stock");
				@Id
				@GeneratedValue(strategy = GenerationType.AUTO)
				@Column(name = "id")
				private Long id;

				private Double numShares;

				private Double price;

				@NotBlank
				@Pattern(regexp="[A-Z]+")
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

				public Double getNumShares() {
								return numShares;
				}

				public void setNumShares(Double numShares) {
								this.numShares = numShares;
				}

				public Double lookup() {
								if (this.getNumShares() == null) {
												this.setNumShaares(1);
								}
								if (this.getSymbol() == null) {
												return 0.00;
								}
								String url = "http://localhost:8081/"+this.getSymbol().toUpperCase();
								try {
												URL obj = new URL(url);
												HttpURLConnection con = (HttpURLConnection) obj.openConnection();
												//optional default is GET
												con.setRequestMethod("GET");

												//add request header
												con.setRequestProperty("User-Agent", "hdStockScreener/1.0");

												int responseCode = con.getResponseCode();
												LOG.info("\nSending 'GET' request to URL : " + url);
												LOG.info("Response Code : " + responseCode);

												BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
												String inputLine;
												StringBuffer response = new StringBuffer();

												while ((inputLine = in.readLine()) != null) {
																response.append(inputLine);
												}
												in.close();
												price = Double.parseDouble((String)new JacksonJsonParser().parseMap(response.toString()).get("price"));
								} catch (MalformedURLException e) {
												LOG.error(url + ": "+e.getMessage());
								} catch (ProtocolException e) {
												LOG.error(url + ": "+e.getMessage());
								} catch (IOException e) {
												LOG.error(e.getMessage());
								} catch (NullPointerException e) {
								}
								return price*this.getNumShares();	
				}

				public Double getPrice() {
								return this.lookup();
				}

				public void setPrice(Double price) {
								// exists for bean spec requirements only
				}

				public String getSymbol() {
								return symbol;
				}

				public void setSymbol(String symbol) {
								this.symbol = symbol.toUpperCase();
								this.setPrice(this.lookup());
				}

				public Date getAdded() {
								return added;
				}

				public void setAdded(Date added) {
								this.added = added;
				}

				public Stock() {
								super();
								this.added = new Date();
				}
				@Override
				public String toString() {
								return "Stock [id=" + id + ", numShares=" + numShares + ", price=" + price + ", symbol=" + symbol + ", added="
												+ added + "]";
				}



}
