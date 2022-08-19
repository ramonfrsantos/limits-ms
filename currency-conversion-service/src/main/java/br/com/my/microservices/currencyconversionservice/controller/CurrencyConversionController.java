package br.com.my.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.my.microservices.currencyconversionservice.bean.CurrencyConversion;
import br.com.my.microservices.currencyconversionservice.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {
  
  @Autowired
  private CurrencyConversionService currencyConversionService;

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
    return currencyConversionService.calculateCurrencyConversion(from, to, quantity);
  }
}
