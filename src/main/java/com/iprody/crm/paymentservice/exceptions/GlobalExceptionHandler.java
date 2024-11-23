package com.iprody.crm.paymentservice.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationErrors(WebExchangeBindException ex) {
        List<String> errorDetails = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .details(errorDetails)
                .build();
        return Mono.just(new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handlePaymentNotFoundException(PaymentNotFoundException ex,
                                                                              ServerWebExchange exchange) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .requestResourceGuid(UUID.fromString(exchange.getRequest().getQueryParams().getFirst("id")))
                .build();
        return Mono.just(new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>>
        handleException(Exception ex, ServerWebExchange exchange) {
        String requestedResourceGuidString = exchange.getRequest().getQueryParams().getFirst("id");
        UUID requestedResourceGuid = StringUtils.isBlank(requestedResourceGuidString)
                ? null : UUID.fromString(requestedResourceGuidString);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .requestResourceGuid(requestedResourceGuid)
                .build();
        return Mono.just(new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

