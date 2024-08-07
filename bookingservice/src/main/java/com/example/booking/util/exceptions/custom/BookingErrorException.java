package com.example.booking.util.exceptions.custom;

public class BookingErrorException extends RuntimeException{
    public BookingErrorException(String message) {
        super(message);
    }
}
