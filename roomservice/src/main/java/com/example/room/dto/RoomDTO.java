package com.example.room.dto;


import com.example.room.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;
    private String roomNumber;
    private Double price;
    private Boolean isAvailable;
    private RoomType type;
}
