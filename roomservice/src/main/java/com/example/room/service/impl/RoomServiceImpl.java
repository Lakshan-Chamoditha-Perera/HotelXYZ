package com.example.room.service.impl;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.Room;
import com.example.room.entity.RoomType;
import com.example.room.entity.Status;
import com.example.room.repo.RoomRepository;
import com.example.room.service.RoomService;
import com.example.room.util.exceptions.RoomAlreadyExistsException;
import com.example.room.util.exceptions.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public  RoomDTO getRoom(Integer id) {
        log.info("Method getRoom called with id {}", id);
        try {
            Room room = roomRepository.findById(id).orElse(new Room());
            return modelMapper.map(room, RoomDTO.class);
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<RoomDTO> getRoomsWithPagination(Integer page, Integer size, String roomNumber, RoomType type, Status availabilityStatus) {
        log.info("Method getRoomsWithPagination called with page {}, size {}, roomNumber {}, type {}, isAvailable {}", page, size, roomNumber, type, availabilityStatus);

        try{
            Pageable pageable = PageRequest.of(page, size);
            return roomRepository.findRoomsWithPagination(roomNumber, type, availabilityStatus, pageable);
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<RoomDTO> getRoomsWithoutPagination(String roomNumber, RoomType type, Status availabilityStatus) {
        log.info("Method getRoomsWithoutPagination called with roomNumber {}, type {}, availabilityStatus {}", roomNumber, type, availabilityStatus);

        try {
            return roomRepository.findRoomsWithoutPagination(roomNumber, type, availabilityStatus).stream()
                    .map((element) -> modelMapper.map(element, RoomDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw e;
        }

    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        log.info("createRoom called with roomNumber {}", roomDTO.getRoomNumber());

        if (roomRepository.existsByRoomNumber(roomDTO.getRoomNumber()))
            throw new RoomAlreadyExistsException("Room with number " + roomDTO.getRoomNumber() + " already exists");

        Room room = modelMapper.map(roomDTO, Room.class);
        room = roomRepository.save(room);
        RoomDTO savedRoomDTO = modelMapper.map(room, RoomDTO.class);

        log.info("createRoom success with id {}", savedRoomDTO.getId());
        return savedRoomDTO;
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO, Integer id) {
        log.info("Method updateRoom called with id {}", roomDTO.getId());

        try {
            Room room = roomRepository.findById(id)
                    .orElseThrow(() -> new RoomNotFoundException("Room not found"));

            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setPrice(roomDTO.getPrice());
            room.setType(roomDTO.getType());

            if (roomDTO.getAvailabilityStatus() != null) {
                room.setAvailabilityStatus(roomDTO.getAvailabilityStatus());
            }

            roomRepository.save(room);

            log.info("updateRoom success with id {}", roomDTO.getId());
            return modelMapper.map(room, RoomDTO.class);
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Boolean deleteRoom(Integer id) {
        log.info("Method deleteRoom called with id {}", id);

        try {
            roomRepository.findById(id)
                    .orElseThrow(() -> new RoomNotFoundException("Room not found"));
            roomRepository.deleteById(id);

            log.info("deleteRoom success with id {}", id);
            return true;
        } catch (Exception e) {
            log.error("ERROR: deleteRoom failed with id {}", id);
            throw e;
        }
    }

}