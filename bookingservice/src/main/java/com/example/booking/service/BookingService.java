package com.example.booking.service;

import com.example.booking.dto.BookingDTO;

public interface BookingService {

    BookingDTO getBooking(Integer id);
    BookingDTO createBooking(BookingDTO bookingDTO);
}

