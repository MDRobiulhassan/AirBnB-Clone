package com.example.AirBnb_Clone.dto.request;

import com.example.AirBnb_Clone.entity.User;
import com.example.AirBnb_Clone.entity.enums.Gender;

public class GuestDTO {
    private User user;
    private String name;
    private Integer age;
    private Gender gender;
}
