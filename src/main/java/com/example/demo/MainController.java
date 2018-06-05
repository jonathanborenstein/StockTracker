package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home(StockTransaction stockTransaction, Model model){
		model.addAttribute("stockTransaction", stockTransaction);
		return "ajax";
	}

}
