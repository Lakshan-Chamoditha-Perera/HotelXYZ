package com.example.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private List<Long> bookingIds;
}