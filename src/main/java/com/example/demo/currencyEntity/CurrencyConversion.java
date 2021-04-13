package com.example.demo.currencyEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CurrencyConverisons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {
@Id
@GeneratedValue

private Long id;
private String countryCode;
private double conversionFactor;
public CurrencyConversion(String countryCode, double d) {
	super();
	this.countryCode = countryCode;
	this.conversionFactor = d;
}
@Override
public String toString() {
	return "CurrencyConversion [id=" + id + ", countryCode=" + countryCode + ", conversionFactor=" + conversionFactor
			+ "]";
}


}
