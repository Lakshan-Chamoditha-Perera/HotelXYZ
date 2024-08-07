package com.example.customer.service.impl;

import com.example.customer.dto.BookingDTO;
import com.example.customer.repo.BookingRepository;
import com.example.customer.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookingDTO getBooking(Integer id) {

        return null;
    }
}
