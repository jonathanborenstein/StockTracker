package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockProjectApplication.class, args);
	}
	
	//populate some data for example
	
	@Bean
	CommandLineRunner command(StockService stockService){
		return args -> {
			
			Stock s1 = new Stock(200, 100, 200, "AAPL");
			stockService.save(s1);
			
			Stock s2 = new Stock(200, 100, 200, "FB");
			stockService.save(s2);
			
			Stock s3 = new Stock(150, 120.75, 150, "NFLX");
			stockService.save(s3);
			
			Stock s4 = new Stock(-100, 150, -100, "AAPL");
			stockService.calcRealizedProfit(s4);
			stockService.save(s4);
			
			Stock s5 = new Stock(-50, 160, -50, "FB");
			stockService.calcRealizedProfit(s5);
			stockService.save(s5);
			
		};
	}
	
	
}
