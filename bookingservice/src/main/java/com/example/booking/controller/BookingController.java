package com.example.booking.controller;

import com.base.base.dto.BookingEventDTO;
import com.example.booking.dto.BookingDTO;
import com.example.booking.kafka.BookingProducer;
import com.example.booking.service.BookingService;
import com.example.booking.payloads.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingProducer bookingProducer;

    @PostMapping
    public ResponseEntity<StandardResponse> createBooking(@RequestBody BookingDTO bookingDTO) {

        BookingEventDTO bookingEventDTO = new BookingEventDTO();
        bookingEventDTO.setMessage("Booking is commited");
        bookingEventDTO.setStatus("pending");
        bookingProducer.sendMessage(bookingEventDTO);

        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Booking created successfully")
                        .data(bookingService.createBooking(bookingDTO))
                        .build()
        );
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<StandardResponse> getBookings() {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Bookings retrieved successfully")
                        .data(bookingService.getBookings())
                        .build()
        );
    }


}
