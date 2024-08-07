package com.example.room.util;

import com.example.room.payloads.StandardResponse;
import com.example.room.util.exceptions.RoomAlreadyExistsException;
import com.example.room.util.exceptions.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleRoomAlreadyExistsException(RoomAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                StandardResponse.builder()
                        .code(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<StandardResponse> handleRoomNotFoundException(RoomNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build()
        );
    }

}
