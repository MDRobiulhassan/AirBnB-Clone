package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
