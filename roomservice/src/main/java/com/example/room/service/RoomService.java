package com.example.room.service;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.RoomType;
import com.example.room.entity.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {

    Page<RoomDTO> getRoomsWithPagination(Integer page, Integer size, String roomNumber, RoomType type, Status availabilityStatus);

    List<RoomDTO> getRoomsWithoutPagination(String roomNumber, RoomType type, Status availabilityStatus);

    RoomDTO createRoom(RoomDTO roomDTO);

    Boolean deleteRoom(Integer roomId);

    RoomDTO getRoom(Integer roomId);

    RoomDTO updateRoom(RoomDTO roomDTO, Integer roomId);

}

