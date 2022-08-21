package br.com.my.microservices.currencyconversionservice.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.my.microservices.currencyconversionservice.bean.CurrencyConversion;
import br.com.my.microservices.currencyconversionservice.bean.CurrencyExchangeProxy;

@Service
public class CurrencyConversionService {

  // proxy é usado para buscar os dados de um objeto através do Feign REST Client
  @Autowired
  private CurrencyExchangeProxy proxy;

  public CurrencyConversion calculateCurrencyConversion(String from, String to, BigDecimal quantity) {
    HashMap<String, String> uriVariables = new HashMap<String, String>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
        "http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

    CurrencyConversion currencyConversion = responseEntity.getBody();

    return new CurrencyConversion(currencyConversion.getId(), from, to,
        currencyConversion.getEnvironment() + " " + "REST template",
        currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()),
        quantity);
  }

  public CurrencyConversion calculateCurrencyConversionFeign(String from, String to, BigDecimal quantity) {
    CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(currencyConversion.getId(), from, to,
        currencyConversion.getEnvironment() + " " + "feign",
        currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()),
        quantity);

  }
}
