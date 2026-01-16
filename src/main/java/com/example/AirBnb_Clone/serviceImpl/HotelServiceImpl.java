package com.example.AirBnb_Clone.serviceImpl;

import com.example.AirBnb_Clone.dto.request.HotelDTO;
import com.example.AirBnb_Clone.dto.response.HotelResponseDTO;
import com.example.AirBnb_Clone.entity.Hotel;
import com.example.AirBnb_Clone.entity.Room;
import com.example.AirBnb_Clone.exceptions.ResourceNotFoundException;
import com.example.AirBnb_Clone.repository.HotelRepository;
import com.example.AirBnb_Clone.service.HotelService;
import com.example.AirBnb_Clone.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public HotelResponseDTO createHotel(HotelDTO hoteldto) {
        log.info("Creating Hotel with Name : {}", hoteldto.getName());
        Hotel hotel = modelMapper.map(hoteldto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Hotel Created with ID : {}", hotel.getId());
        return modelMapper.map(hotel, HotelResponseDTO.class);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {
        log.info("Getting All Hotels");
        List<Hotel> hotels = hotelRepository.findAll();
        log.info("All Hotels Found : {}", hotels);
        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        log.info("Getting Hotel with ID : {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID :" + id));
        log.info("Hotel Found : {}", hotel);
        return modelMapper.map(hotel, HotelResponseDTO.class);
    }

    @Override
    public HotelResponseDTO updateHotel(HotelDTO hoteldto, Long id) {
        log.info("Updating Hotel with ID : {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID :" + id));
        modelMapper.map(hoteldto, hotel);
        Hotel savedHotel = hotelRepository.save(hotel);
        log.info("Hotel Updated with ID : {}", savedHotel);
        return modelMapper.map(savedHotel, HotelResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID :" + id));

        hotelRepository.deleteById(id);

        for (Room room : hotel.getRooms()) {
            inventoryService.deleteFutureInventories(room);
        }
    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        log.info("Activating Hotel with ID : {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID :" + id));
        hotel.setActive(true);

        for (Room room : hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }

}
