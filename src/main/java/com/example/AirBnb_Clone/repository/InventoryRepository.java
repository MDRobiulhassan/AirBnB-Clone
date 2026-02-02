package com.example.AirBnb_Clone.repository;

import com.example.AirBnb_Clone.entity.Hotel;
import com.example.AirBnb_Clone.entity.Inventory;
import com.example.AirBnb_Clone.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByRoom(Room room);

    @Query("""
                SELECT i.hotel
                FROM Inventory i
                WHERE i.hotel.city = :city
                  AND i.date >= :checkInDate
                  AND i.date <= :checkOutDate
                  AND i.closed = false
                  AND (i.totalCount - i.bookedCount) >= :numberOfRooms
                GROUP BY i.hotel
                HAVING COUNT(DISTINCT i.date) = :dateCount
            """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("numberOfRooms") Integer numberOfRooms,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

}
