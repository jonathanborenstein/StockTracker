package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

	List<StockTransaction> findBySymbol(String symbol);
	
	List<StockTransaction> findAllByOrderBySymbolAsc();

	

}
