package com.example.booking.controller;

import com.example.booking.dto.BookingDTO;
import com.example.booking.service.BookingService;
import com.example.booking.payloads.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<StandardResponse> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Booking created successfully")
                        .data(bookingService.createBooking(bookingDTO))
                        .build()
        );
    }
}
