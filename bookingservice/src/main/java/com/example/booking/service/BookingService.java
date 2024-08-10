package com.example.booking.service;

import com.example.booking.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    BookingDTO getBooking(Integer id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getBookings();

}

