package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner command(StockTransactionRepository stockRepo, StockService stockService) {
		return args -> {

		

			StockTransaction transaction1 = new StockTransaction(100, 100, new BigDecimal(100.25), "GOOG");
			StockTransaction transaction2 = new StockTransaction(100, 100, new BigDecimal(100.25), "AAPL");
			StockTransaction transaction5 = new StockTransaction(100, 100, new BigDecimal(100.33), "AAPL");
			StockTransaction transaction7 = new StockTransaction(100, 100, new BigDecimal(10.25), "AAPL");



			StockTransaction transaction3 = new StockTransaction(100, 100, new BigDecimal(100.777), "FB");

			StockTransaction transaction4 = new StockTransaction(100, 100, new BigDecimal(100.22), "MSFT");
			StockTransaction transaction6 = new StockTransaction(50, 50, new BigDecimal(120.22), "MSFT");


			stockRepo.save(transaction1);
			stockRepo.save(transaction2);
			stockRepo.save(transaction3);
			stockRepo.save(transaction4);
			stockRepo.save(transaction5);
			stockRepo.save(transaction6);
			stockRepo.save(transaction7);
			
		};
	}

}



