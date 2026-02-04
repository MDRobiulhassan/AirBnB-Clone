package com.example.AirBnb_Clone.controller;

import com.example.AirBnb_Clone.dto.request.BookingRequestDTO;
import com.example.AirBnb_Clone.dto.request.GuestDTO;
import com.example.AirBnb_Clone.service.HotelBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final HotelBookingService hotelBookingService;

    @PostMapping("/init")
    public ResponseEntity<?> initialiseBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelBookingService.initialiseBooking(bookingRequestDTO));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<?> addGuests(@PathVariable Long bookingId, @RequestBody List<GuestDTO> guestDTO) {
        return ResponseEntity.ok(hotelBookingService.addGuests(bookingId, guestDTO));
    }

}
