package com.example.room.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status availabilityStatus;

    @Enumerated(EnumType.STRING)
    private RoomType type;

}