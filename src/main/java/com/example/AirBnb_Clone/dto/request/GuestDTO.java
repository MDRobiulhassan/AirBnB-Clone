package com.example.AirBnb_Clone.dto.request;

import com.example.AirBnb_Clone.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDTO {
    private String name;
    private Integer age;
    private Gender gender;
}
