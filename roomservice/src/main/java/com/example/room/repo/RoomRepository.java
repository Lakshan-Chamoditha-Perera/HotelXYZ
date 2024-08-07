package com.example.room.repo;

import com.example.room.dto.RoomDTO;
import com.example.room.entity.Room;
import com.example.room.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Boolean existsByRoomNumber(String roomNumber);

    @Query("SELECT new com.example.room.dto.RoomDTO(r.id, r.roomNumber, r.price, r.isAvailable, r.type) " +
            "FROM Room r " +
            "WHERE (?1 IS NULL OR r.roomNumber LIKE %?1%) " +
            "AND (?2 IS NULL OR r.type = ?2) " +
            "AND (?3 IS NULL OR r.isAvailable = ?3)")
    Page<RoomDTO> findRoomsWithPagination(String roomNumber, RoomType type, Boolean isAvailable, Pageable pageable);

    @Query(value = "SELECT * FROM room " +
            "WHERE (?1 IS NULL OR room_number LIKE %?1%) " +
            "AND (?2 IS NULL OR type = ?2) " +
            "AND (?3 IS NULL OR is_available = ?3)", nativeQuery = true)
    List<Room> findRoomsWithoutPagination(String roomNumber, RoomType type, Boolean isAvailable);

}
