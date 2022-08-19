package br.com.my.microservices.currencyconversionservice.bean;

import java.math.BigDecimal;

public class CurrencyConversion {

  private Long id;
  private String from; 
  private String to; 
  private String environment; 
  private BigDecimal conversionMultiple;
  private BigDecimal totalCalculatedAmount;
  private BigDecimal quantity;

  public CurrencyConversion(){
    
  }

  public CurrencyConversion(Long id, String from, String to, String environment, BigDecimal conversionMultiple,
      BigDecimal totalCalculatedAmount, BigDecimal quantity) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.environment = environment;
    this.conversionMultiple = conversionMultiple;
    this.totalCalculatedAmount = totalCalculatedAmount;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public BigDecimal getConversionMultiple() {
    return conversionMultiple;
  }

  public void setConversionMultiple(BigDecimal conversionMultiple) {
    this.conversionMultiple = conversionMultiple;
  }

  public BigDecimal getTotalCalculatedAmount() {
    return totalCalculatedAmount;
  }

  public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
    this.totalCalculatedAmount = totalCalculatedAmount;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  
}
