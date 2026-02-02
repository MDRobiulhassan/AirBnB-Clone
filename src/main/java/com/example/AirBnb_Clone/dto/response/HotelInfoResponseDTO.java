package com.example.AirBnb_Clone.dto.response;

import com.example.AirBnb_Clone.dto.request.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoResponseDTO {
    private HotelResponseDTO hotel;
    private List<RoomResponseDTO> rooms;
}
