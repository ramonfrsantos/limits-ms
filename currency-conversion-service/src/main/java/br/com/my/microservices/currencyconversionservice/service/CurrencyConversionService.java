package br.com.my.microservices.currencyconversionservice.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.my.microservices.currencyconversionservice.bean.CurrencyConversion;

@Service
public class CurrencyConversionService {

  public CurrencyConversion calculateCurrencyConversion(String from, String to, BigDecimal quantity) {
    HashMap<String, String> uriVariables = new HashMap<String, String>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
        "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();

    return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getEnvironment(), currencyConversion.getConversionMultiple(), quantity.multiply(currencyConversion.getConversionMultiple()), quantity);
  }
}
