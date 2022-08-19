package br.com.my.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.my.microservices.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
  CurrencyExchange findByFromAndTo(String from, String to);
}