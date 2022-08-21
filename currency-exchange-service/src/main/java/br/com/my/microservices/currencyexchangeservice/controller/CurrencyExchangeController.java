package br.com.my.microservices.currencyexchangeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.my.microservices.currencyexchangeservice.bean.CurrencyExchange;
import br.com.my.microservices.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

  @Autowired
  private CurrencyExchangeService currencyExchangeService;
  
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
    return currencyExchangeService.retrieveExchangeValue(from, to);
  }

  @GetMapping("/currency-exchange")
  public List<CurrencyExchange> findAll(){
    return currencyExchangeService.findAll();
  }

  @PostMapping("/currency-exchange")
  public CurrencyExchange create(@RequestBody CurrencyExchange currencyExchange){
    return currencyExchangeService.create(currencyExchange);
  }

  @PutMapping("/currency-exchange")
  public CurrencyExchange update(@RequestBody CurrencyExchange currencyExchange){
    return currencyExchangeService.update(currencyExchange);
  }
}
