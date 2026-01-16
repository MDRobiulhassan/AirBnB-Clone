package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
