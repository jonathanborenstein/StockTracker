package com.example;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class StockController {


	@Autowired
	private StockService stockService;


	@RequestMapping(value="/stocks", method=RequestMethod.GET)
	String stocks(Model model) {

		Stock stock = new Stock();

		model.addAttribute("stock", stock);
		return "stocks";
	}

	@RequestMapping(value="/stocks", method=RequestMethod.POST)
	String stocks(Model model, @ModelAttribute(value="stock") @Valid Stock stock, BindingResult result) {
		
		
		
		if (stock.getNumShares() < 0){
			stockService.calcRealizedProfit(stock);
		}

		if(!result.hasErrors()) {
			stockService.save(stock);
		}
		return "redirect:/generate";
	}

	@RequestMapping(value="/generate", method=RequestMethod.GET)
	String generate(Model model){
		

		List<Stock> stockList = stockService.findAll();
		List<StockTotalObject> stockTotalList = stockService.getStockInfo(stockList);
		String totalProfit = stockService.getTotalProfit(stockTotalList);
		List<StockRealized> realized = stockService.getRealizedProfits();


		model.addAttribute("list", stockTotalList);
		model.addAttribute("stock", stockList);
		model.addAttribute("profit", totalProfit);
		model.addAttribute("realized", realized);

	


		return "stockinfo";
	}
}