package com.example.AirBnb_Clone.dto.response;

import com.example.AirBnb_Clone.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
}