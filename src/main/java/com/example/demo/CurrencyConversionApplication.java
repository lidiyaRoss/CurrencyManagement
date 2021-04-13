package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.currencyEntity.CurrencyConversion;
import com.example.demo.currencyRepository.CurrencyRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyConversionApplication {

	@Autowired
	CurrencyRepository currencyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionApplication.class, args);
	}
	
	@PostConstruct
	public void dataInsert() {
		currencyRepository.save(new CurrencyConversion("INR", 74.742055));
		currencyRepository.save(new CurrencyConversion("CAD", 1.252890));
	}
	

}
