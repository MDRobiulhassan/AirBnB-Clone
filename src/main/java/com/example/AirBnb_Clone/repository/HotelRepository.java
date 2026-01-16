package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
