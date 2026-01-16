package com.example.AirBnb_Clone.dto.response;

import com.example.AirBnb_Clone.entity.HotelContactInfo;
import com.example.AirBnb_Clone.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelResponseDTO {
    private Long id;
    private String name;
    private String city;
    private List<String> photos;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<String> amenities;
    private Boolean active;
    private HotelContactInfo contactInfo;
}
