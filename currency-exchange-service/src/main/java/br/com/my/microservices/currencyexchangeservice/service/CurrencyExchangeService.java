package br.com.my.microservices.currencyexchangeservice.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.my.microservices.currencyexchangeservice.bean.CurrencyExchange;
import br.com.my.microservices.currencyexchangeservice.exception.CurrencyExchangeNotFoundException;
import br.com.my.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

  private Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);
  @Autowired
  private CurrencyExchangeRepository currencyExchangeRepository;

  @Autowired
  private Environment environment;

  public CurrencyExchange retrieveExchangeValue(String from, String to){
    String port = environment.getProperty("local.server.port");

    logger.info("retrieveExchangeValue called with {} to {}", from, to);

    CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
    if(Objects.isNull(currencyExchange)){
      throw new CurrencyExchangeNotFoundException("Currency exchange not found in system.");
    }

    // new CurrencyExchange(from, to, BigDecimal.valueOf(50));
    currencyExchange.setEnvironment(port);

    return currencyExchangeRepository.save(currencyExchange);
  }
}