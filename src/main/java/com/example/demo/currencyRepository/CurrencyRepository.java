package com.example.demo.currencyRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.currencyEntity.CurrencyConversion;

public interface CurrencyRepository extends JpaRepository<CurrencyConversion, Long> {

	public CurrencyConversion findByCountryCode(String countryCode);
}
