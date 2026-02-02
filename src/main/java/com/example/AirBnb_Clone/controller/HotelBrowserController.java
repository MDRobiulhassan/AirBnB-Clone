package com.example.AirBnb_Clone.controller;

import com.example.AirBnb_Clone.dto.request.HotelSearchRequest;
import com.example.AirBnb_Clone.dto.response.HotelResponseDTO;
import com.example.AirBnb_Clone.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowserController {

    private final InventoryService inventoryService;

    @PostMapping("/search")
    public ResponseEntity<?> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelResponseDTO> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);

    }
}
