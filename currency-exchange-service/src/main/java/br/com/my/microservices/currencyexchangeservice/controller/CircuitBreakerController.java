package br.com.my.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {

  private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  // Circuit Breaker retorna a response mesmo sem executar a request, 
  // após um determinado número consecutivo de chamadas, 

  @GetMapping("/sample-api")
  // @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
  @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
  // @RateLimiter(name="default")
  // @Bulkhead(name="default")
  // 10s => permite apenas 1000 chamadas para sample-api
  public String sampleApi() {

    // se existe alguma falha nesse metodo ele tenta chamar de novo 3 vezes
    // se falhar nas 3 vezes, então retorna o erro
    // no application.properties da pra alterar o numero de vezes
    // tempo de espera, etc.
    logger.info("Sample API call received");

    // acessar uma URL que retorna erro
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/dummy-url",
        String.class);
        
    return forEntity.getBody();

    // return "Sample API";
  }

  public String hardcodedResponse(Exception ex){
    return "Fallback response";
  }
}
