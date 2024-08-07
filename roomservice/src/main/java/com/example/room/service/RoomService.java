package com.example.room.service;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.RoomType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {

    Page<RoomDTO> getRoomsWithPagination(Integer page, Integer size, String roomNumber, RoomType type, Boolean isAvailable);

    List<RoomDTO> getRoomsWithoutPagination(String roomNumber, RoomType type, Boolean isAvailable);

    RoomDTO createRoom(RoomDTO roomDTO);

    Boolean deleteRoom(Integer roomId);

    RoomDTO getRoom(Integer roomId);

    RoomDTO updateRoom(RoomDTO roomDTO, Integer roomId);

}

