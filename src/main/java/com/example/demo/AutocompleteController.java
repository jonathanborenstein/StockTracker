package com.example.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AutocompleteController {

	private final SymbolRepository symbolRepository;

	

	public AutocompleteController(SymbolRepository symbolRepository) {
		this.symbolRepository = symbolRepository;
	}


	@RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SuggestionWrapper autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {

		List<String> suggestions = new ArrayList<>();

		List<String> symbols = symbolRepository.findAll().stream()
		.map(e -> e.getSymbol())
		.collect(Collectors.toList());
		
		for (String symbol : symbols) {

			if (symbol.toLowerCase().contains(searchstr.toLowerCase())) {
				suggestions.add(symbol);
			}
		}

		int n = suggestions.size() > 20 ? 20 : suggestions.size();
		List<String> sulb = new ArrayList<>(suggestions).subList(0, n);

		SuggestionWrapper sw = new SuggestionWrapper();
		sw.setSuggestions(sulb);
		return sw;
	}


}
