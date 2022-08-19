package br.com.my.microservices.currencyexchangeservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// aplica-se a todos os controllers
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  // personalizar response da exception (generic)
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // personalizar response da exception (usuário não encontrado)
  @ExceptionHandler(CurrencyExchangeNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundExceptions(CurrencyExchangeNotFoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  // lidar com erro de validação e personalizar a mensagem de erro
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String detailsMessage = ex.getBindingResult()
        .getAllErrors().toString().replace("[", "").replace("]", "").split("default message ")[2];
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed.", detailsMessage);

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

}