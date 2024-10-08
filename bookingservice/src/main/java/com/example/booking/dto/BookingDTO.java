package com.example.booking.dto;

import com.example.booking.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private Long customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalAmount;
    private BookingStatus status;

    private List<Long> roomIds;
}