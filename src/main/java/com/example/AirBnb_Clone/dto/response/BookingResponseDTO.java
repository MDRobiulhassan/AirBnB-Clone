package com.example.AirBnb_Clone.dto.response;

import com.example.AirBnb_Clone.entity.*;
import com.example.AirBnb_Clone.entity.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private HotelResponseDTO hotel;
    private RoomResponseDTO room;
    private Integer numberOfRooms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Payment paymentId;
//    private Set<GuestResponseDTO> guests;
}
