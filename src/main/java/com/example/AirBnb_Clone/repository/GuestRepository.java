package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
