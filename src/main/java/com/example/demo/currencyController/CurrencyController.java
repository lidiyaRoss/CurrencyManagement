package com.example.demo.currencyController;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.currencyEntity.CurrencyConversion;
import com.example.demo.currencyService.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	Environment env;
	
	@PostMapping("/saveConversion")
	public ResponseEntity<String> saveConversion(@RequestBody CurrencyConversion currencyConversion){
		return currencyService.saveConversion(currencyConversion);
	}
	
	@GetMapping("/getConversion/{countryCode}")
	public double getCoversion(@PathVariable String countryCode) throws ServerException {
		try{
			log.info("\n\n inside Get conversion method{}", countryCode);
			log.info("CurrencyManagement Server port : {}" , env.getProperty("local.server.port"));
		
			return currencyService.getConversion(countryCode);
	
		} catch(Exception e) {
			
			throw new ServerException(e.getMessage());
		}
	}
	
	@PutMapping("/updateConversion")
	public ResponseEntity<String> updateConversion(@RequestBody CurrencyConversion currencyConversion){
		return currencyService.updateConversion(currencyConversion);
	}
}
