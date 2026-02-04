package com.example.AirBnb_Clone.service;

import com.example.AirBnb_Clone.dto.request.BookingRequestDTO;
import com.example.AirBnb_Clone.dto.request.GuestDTO;
import com.example.AirBnb_Clone.dto.response.BookingResponseDTO;
import com.example.AirBnb_Clone.dto.response.GuestResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface HotelBookingService {
    BookingResponseDTO initialiseBooking(BookingRequestDTO bookingRequestDTO);

    List<GuestResponseDTO> addGuests(Long bookingId, List<GuestDTO> guestDTO);
}
