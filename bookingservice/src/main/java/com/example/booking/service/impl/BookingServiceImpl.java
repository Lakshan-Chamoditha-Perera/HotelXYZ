package com.example.booking.service.impl;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.repo.BookingRepository;
import com.example.customer.dto.CustomerDTO;
import com.example.booking.service.BookingService;
import com.example.customer.util.exceptions.custom.CustomerNotFoundException;
import com.example.room.dto.RoomDTO;
import com.example.room.entity.Status;
import com.example.room.util.exceptions.RoomNotAvailableException;
import com.example.room.util.exceptions.RoomNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final WebClient customerWebClient;
    private final WebClient roomWebClient;

    @Override
    public BookingDTO getBooking(Integer id) {
        return null;
    }


    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        log.info("Method createBooking called " + bookingDTO);
        try{

            // 1. Get Customer Information
            CustomerDTO customerDTO = customerWebClient
                    .get()
                    .uri("/customers/{customerId}", bookingDTO.getCustomerId())
                    .retrieve()
                    .bodyToMono(CustomerDTO.class)
                    .block();

            if (customerDTO==null || customerDTO.getId() ==null || customerDTO.getId() == 0) {
                throw new CustomerNotFoundException("Customer not found for ID: " + bookingDTO.getCustomerId());
            }
            System.out.println("CustomerDTO: " + customerDTO);

            // 2. Validate Rooms and Calculate Total Amount
            Double totalAmount = 0.0;
            List<RoomDTO> bookedRooms = new ArrayList<>();
            for (Long roomId : bookingDTO.getRoomIds()) {
                RoomDTO roomDTO = roomWebClient
                        .get()
                        .uri("/rooms/{roomId}", roomId)
                        .retrieve()
                        .bodyToMono(RoomDTO.class)
                        .block();

                if (roomDTO == null || roomDTO.getId() == null || roomDTO.getId() == 0) {
                    throw new RoomNotFoundException("Room not found for ID: " + roomId);
                }

                switch (roomDTO.getAvailabilityStatus()){
                    case NOT_AVAILABLE:
                        throw new RoomNotAvailableException("Room with ID " + roomId + " is not available for the specified dates.");
                    case PENDING:
                        throw  new RoomNotAvailableException("Room with ID " + roomId + " is under review.");
                }
                bookedRooms.add(roomDTO);
            }


            // 3. Create and Save Booking
            Booking booking = modelMapper.map(bookingDTO, Booking.class);
            bookingRepository.save(booking);

            // 4. Update Customer with Booking ID (If required)
            if (customerDTO.getBookingIds() == null) {
                customerDTO.setBookingIds(new ArrayList<>());
            }
            customerDTO.getBookingIds().add(booking.getId());
            customerWebClient
                    .patch()
                    .uri("/customers/{customerId}", bookingDTO.getCustomerId())
                    .bodyValue(customerDTO)
                    .retrieve()
                    .bodyToMono(CustomerDTO.class)
                    .block();

            //5. set isAvailable to false in rooms
            for (RoomDTO roomDTO : bookedRooms) {
                roomDTO.setAvailabilityStatus(Status.NOT_AVAILABLE);
                roomWebClient
                        .patch()
                        .uri("/rooms/{roomId}", roomDTO.getId())
                        .bodyValue(roomDTO)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }

            // 6. Return Booking Response
            return modelMapper.map(booking, BookingDTO.class);

        } catch (WebClientResponseException e){
            log.error("ERROR: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<BookingDTO> getBookings() {
        try {
            log.info("Method getBookings called");
            return bookingRepository
                    .findAll()
                    .stream()
                    .map((element) -> modelMapper.map(element, BookingDTO.class))
                    .collect(Collectors.toList());
        }catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
         throw e;
        }
    }

}
