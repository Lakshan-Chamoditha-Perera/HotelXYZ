package com.example.customer.util.exceptions;

import com.example.customer.payloads.StandardResponse;
import com.example.customer.util.exceptions.custom.CustomerAlreadyExistsException;
import com.example.customer.util.exceptions.custom.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.OK).body(
                StandardResponse.builder()
                        .code(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<StandardResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }


}
