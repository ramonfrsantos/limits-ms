package br.com.my.microservices.currencyconversionservice.bean;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign através do Eureka produz load balancer com spring cloud,
// descartando o uso de um hardcoded url

// @FeignClient(name="currency-exchange", url="localhost:8000")
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
  
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
