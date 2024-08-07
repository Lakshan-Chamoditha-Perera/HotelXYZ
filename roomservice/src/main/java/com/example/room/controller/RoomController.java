package com.example.room.controller;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.RoomType;
import com.example.room.payloads.StandardResponse;
import com.example.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<StandardResponse> getRooms(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "roomNumber", required = false) String roomNumber,
            @RequestParam(name = "type", required = false) RoomType type,
            @RequestParam(name = "isAvailable", required = false) Boolean isAvailable) {

        if (size == 0) {
            return ResponseEntity.ok(StandardResponse.builder()
                    .code(200)
                    .message("Rooms retrieved successfully")
                    .data(roomService.getRoomsWithoutPagination(roomNumber, type, isAvailable))
                    .build());
        }

        return ResponseEntity.ok(StandardResponse.builder()
                .code(200)
                .message("Rooms retrieved successfully")
                .data(roomService.getRoomsWithPagination(page, size, roomNumber, type, isAvailable))
                .build());
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Room created successfully")
                        .data(roomService.createRoom(roomDTO))
                        .build()
        );
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<StandardResponse> deleteRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Room deleted successfully")
                        .data(roomService.deleteRoom(roomId))
                        .build()
        );
    }

    @GetMapping("/{roomId}")
    public RoomDTO getRoom(@PathVariable Integer roomId) {
        return roomService.getRoom(roomId);
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<StandardResponse> updateRoom(@PathVariable Integer roomId, @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .code(200)
                        .message("Room updated successfully")
                        .data(roomService.updateRoom(roomDTO, roomId))
                        .build()
        );
    }
}
