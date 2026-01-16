package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
