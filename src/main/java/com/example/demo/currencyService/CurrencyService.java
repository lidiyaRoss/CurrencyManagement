package com.example.demo.currencyService;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.currencyEntity.CurrencyConversion;
import com.example.demo.currencyRepository.CurrencyRepository;
import com.example.demo.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyService {

	@Autowired
	CurrencyRepository currencyRepository;
	
	public ResponseEntity<String> saveConversion(CurrencyConversion currencyConversion){
		try {
			if(null != currencyConversion) {
				CurrencyConversion existingCurrencyConversion = currencyRepository.findByCountryCode(currencyConversion.getCountryCode());
				if(null == existingCurrencyConversion) {
					currencyRepository.save(currencyConversion);
				} else {
					return new ResponseEntity<>("Currency conversion record existing.", HttpStatus.CONFLICT);
				}
			}
			return new ResponseEntity<>("Currency conversion details added.", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Error in adding conversion record.", HttpStatus.BAD_REQUEST);
		}
	}
	
	public double getConversion(String countryCode) throws ServerException{
		CurrencyConversion currencyConversion = new CurrencyConversion();
		try {
			currencyConversion = currencyRepository.findByCountryCode(countryCode);
			log.info("{}", currencyConversion);
			if(null == currencyConversion) {
				throw new ServerException("Conversion factor not found for the given country code");
			}
			
		} catch(Exception e) {
			throw new ServerException(e.getMessage());
		}
		return currencyConversion.getConversionFactor();
	}
	
	public ResponseEntity<String> updateConversion(CurrencyConversion currencyConversion){
		try {
			if(null != currencyConversion) {
				CurrencyConversion existingCurrencyConversion = currencyRepository.findByCountryCode(currencyConversion.getCountryCode());
				if(null == existingCurrencyConversion) {
					throw new ServiceException("Record not found with the given country code.");
				} else {
					existingCurrencyConversion.setConversionFactor(currencyConversion.getConversionFactor());
					currencyRepository.save(existingCurrencyConversion);
				}
			}
			return new ResponseEntity<>("Updation done succeessfully", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Updation failed", HttpStatus.BAD_REQUEST);
		}
	}
}
