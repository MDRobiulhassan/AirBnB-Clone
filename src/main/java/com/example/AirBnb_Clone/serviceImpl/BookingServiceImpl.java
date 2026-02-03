package com.example.AirBnb_Clone.serviceImpl;

import com.example.AirBnb_Clone.dto.request.BookingRequestDTO;
import com.example.AirBnb_Clone.dto.response.BookingResponseDTO;
import com.example.AirBnb_Clone.entity.*;
import com.example.AirBnb_Clone.entity.enums.BookingStatus;
import com.example.AirBnb_Clone.repository.BookingRepository;
import com.example.AirBnb_Clone.repository.HotelRepository;
import com.example.AirBnb_Clone.repository.InventoryRepository;
import com.example.AirBnb_Clone.repository.RoomRepository;
import com.example.AirBnb_Clone.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public BookingResponseDTO initialiseBooking(BookingRequestDTO bookingRequestDTO) {
        log.info("initialising booking for Hotel: {} and Room: {} and date: {}-{}", bookingRequestDTO.getHotelId(), bookingRequestDTO.getRoomId(), bookingRequestDTO.getCheckInDate(), bookingRequestDTO.getCheckOutDate());
        Hotel hotel = hotelRepository.findById(bookingRequestDTO.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found"));
        Room room = roomRepository.findById(bookingRequestDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventories(room.getId(), bookingRequestDTO.getCheckInDate(), bookingRequestDTO.getCheckOutDate(), bookingRequestDTO.getNumberOfRooms());

        long daysCount = ChronoUnit.DAYS.between(bookingRequestDTO.getCheckInDate(), bookingRequestDTO.getCheckOutDate()) + 1;
        if (inventoryList.size() != daysCount) {
            log.info("Requested days: {}, Inventory rows found: {}", daysCount, inventoryList.size());
            throw new RuntimeException("Rooms not available");
        }

        for (Inventory inventory : inventoryList) {
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequestDTO.getNumberOfRooms());
        }

        inventoryRepository.saveAll(inventoryList);

        User user = new User();
        user.setId(1L);

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .user(user)
                .checkInDate(bookingRequestDTO.getCheckInDate())
                .checkOutDate(bookingRequestDTO.getCheckOutDate())
                .roomsCount(bookingRequestDTO.getNumberOfRooms())
                .amount(BigDecimal.TEN)
                .build();

        booking = bookingRepository.save(booking);
        log.info("Booking initialised with ID: {}", booking.getId());
        modelMapper.typeMap(Booking.class, BookingResponseDTO.class)
                .addMapping(Booking::getRoomsCount, BookingResponseDTO::setNumberOfRooms);

        return modelMapper.map(booking, BookingResponseDTO.class);
    }
}
