package com.example.AirBnb_Clone.dto.response;

import com.example.AirBnb_Clone.entity.User;
import com.example.AirBnb_Clone.entity.enums.Gender;

public class GuestResponseDTO {
    private Long id;
    private User user;
    private String name;
    private Integer age;
    private Gender gender;
}