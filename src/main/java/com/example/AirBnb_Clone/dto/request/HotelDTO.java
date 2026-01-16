package com.example.AirBnb_Clone.dto.request;

import com.example.AirBnb_Clone.entity.HotelContactInfo;
import com.example.AirBnb_Clone.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {
    private String name;
    private String city;
    private List<String> photos;
    private List<String> amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
