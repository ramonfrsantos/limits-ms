package br.com.my.microservices.apigateway.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

  private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  // mostra no console o log com a url da request e então executa a request
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    
    logger.info("Path of the request received - {}", exchange.getRequest().getPath());

    return chain.filter(exchange);
  }
  
}
