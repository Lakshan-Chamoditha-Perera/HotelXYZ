package com.example.room.service.impl;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.Room;
import com.example.room.repo.RoomRepository;
import com.example.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomDTO getRoom(Integer id) {
        log.info("getRoom called with id {}", id);
        try {
            Optional<Room> room = roomRepository.findById(id);
            if (room.isPresent()) {
                return modelMapper.map(room.get(), RoomDTO.class);
            }
            return null;
        } catch (Exception e) {
            log.error("getRoom failed with id {}", id);
            throw e;
        }
    }
}
