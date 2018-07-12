package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	private final StockTransactionRepository stockTransactionRepository;
	private final RealizedStockRepository realizedStockRepository;
	private final StockService stockService;


	public StockController(StockTransactionRepository stockTransactionRepository,
			RealizedStockRepository realizedStockRepository, StockService stockService) {
		this.stockTransactionRepository = stockTransactionRepository;
		this.realizedStockRepository = realizedStockRepository;
		this.stockService = stockService;
	}

	@GetMapping("/current")
	public List<StockTotalObject> currentStockInfo() {
		List<StockTransaction> stockList = stockTransactionRepository.findAllByOrderBySymbolAsc();
		List<StockTotalObject> stockTotalObjectList = stockService.getStockInfo(stockList);

		return stockTotalObjectList;
	}
	
	@GetMapping("/profits")
	public String profits() {
		
		List<StockTransaction> stockList = stockTransactionRepository.findAllByOrderBySymbolAsc();
		List<StockTotalObject> stockTotalObjectList = stockService.getStockInfo(stockList);
		
		String profit = stockService.getTotalProfit(stockTotalObjectList);

		return profit;
	}

	@GetMapping("/realized")
	public List<StockRealized> realizedProfits() {
		List<StockRealized> realizedList = realizedStockRepository.findAll();
		return realizedList;

	}

	@GetMapping("/transactions")
	public List<StockTransaction> transactions() {
		List<StockTransaction> transactions = stockTransactionRepository.findAllByOrderBySymbolAsc();
		return transactions;

	}

	@PostMapping("/post")
	public ResponseEntity<?> post(@Valid @RequestBody StockTransaction st, Errors errors){
		

		if (errors.hasErrors() || stockService.getCurrentPrice(st.getSymbol()).compareTo(BigDecimal.ZERO) == 0 
				|| st.getNumOfShares() == 0) {
			return ResponseEntity.badRequest().body("Bad Request");
		}

		st.setSymbol(st.getSymbol().toUpperCase());

		if (st.getState().name().equals("PURCHASE"))
			st.setSharesInLot(st.getNumOfShares());
		
		if (st.getState().name().equals("SALE")){
			stockService.calcRealizedProfit(st);
		}


		return ResponseEntity.ok(stockTransactionRepository.save(st));


	}

}

















