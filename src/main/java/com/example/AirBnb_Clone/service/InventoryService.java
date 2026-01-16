package com.example.AirBnb_Clone.service;

import com.example.AirBnb_Clone.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
}
