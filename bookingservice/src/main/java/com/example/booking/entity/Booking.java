package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ElementCollection
    @CollectionTable(name = "booking_rooms", joinColumns = @JoinColumn(name = "booking_id"))
    private List<Long> roomIds;

}