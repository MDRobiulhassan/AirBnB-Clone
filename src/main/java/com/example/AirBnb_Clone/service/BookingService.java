package com.example.AirBnb_Clone.service;

import com.example.AirBnb_Clone.dto.request.BookingRequestDTO;
import com.example.AirBnb_Clone.dto.response.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO initialiseBooking(BookingRequestDTO bookingRequestDTO);
}
