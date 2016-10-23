package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
	
	List<Stock> findBySymbol(String symbol);
	
	List<Stock> findAllByOrderBySymbolAsc();
	

	
}

