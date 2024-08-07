package com.example.booking.util.exceptions;


import com.example.booking.util.exceptions.custom.InvalidBookingRequestException;
import com.example.customer.util.exceptions.custom.CustomerAlreadyExistsException;
import com.example.customer.util.exceptions.custom.CustomerNotFoundException;
import com.example.customer.payloads.StandardResponse;
import com.example.room.util.exceptions.RoomNotAvailableException;
import com.example.room.util.exceptions.RoomNotFoundException;
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

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<StandardResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<StandardResponse> handleCustomerNotFoundException(RoomNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<StandardResponse> handleRoomNotAvailableException(RoomNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                StandardResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(InvalidBookingRequestException.class)
    public ResponseEntity<StandardResponse> handleInvalidBookingRequestException(InvalidBookingRequestException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }




}