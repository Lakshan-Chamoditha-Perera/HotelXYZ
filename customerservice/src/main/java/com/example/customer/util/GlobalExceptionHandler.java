package com.example.customer.util;

import com.example.customer.payloads.StandardResponse;
import com.example.customer.util.exceptions.CustomerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                StandardResponse.builder()
                        .code(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .build()
        );
    }

}
