package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StockTransaction.State;

@RestController
public class StockController {

	private final StockTransactionRepository stockTransactionRepository;
	private final RealizedStockRepository realizedStockRepository;
	private final SymbolRepository symbolRepository;
	private final StockService stockService;


	public StockController(StockTransactionRepository stockTransactionRepository,
			RealizedStockRepository realizedStockRepository, SymbolRepository symbolRepository,
			StockService stockService) {
		this.stockTransactionRepository = stockTransactionRepository;
		this.realizedStockRepository = realizedStockRepository;
		this.symbolRepository = symbolRepository;
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
	
	@GetMapping("/sample")
	public ResponseEntity<?> sample(){
		
		String[] array = {"AAPL", "TWTR", "NFLX", "TSLA", "AMD", "NVDA", "FB", "UBER", "GE", "AMZN"};
		
		System.out.println((int) (Math.random() * array.length));
		
		String random = array[(int) (Math.random() * array.length)];
		System.out.println(random);
		
		StockTransaction st = new StockTransaction();
		st.setSymbol(random);
		st.setNumOfShares(100);
		st.setSharesInLot(st.getNumOfShares());
		st.setState(State.PURCHASE);
		st.setPriceOfShares(stockService.getCurrentPrice(random));
		

		return ResponseEntity.ok(stockTransactionRepository.save(st));


	}

}

















