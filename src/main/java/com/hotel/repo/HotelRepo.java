package com.hotel.repo;

import com.hotel.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface HotelRepo extends JpaRepository<HotelModel,Long> {

    @Query("""
    SELECT DISTINCT h
    FROM HotelModel h
    JOIN h.rooms r
    WHERE LOWER(h.address)
     Like Lower(CONCAT('%',:address,'%'))
    AND NOT EXISTS(
    
        SELECT b
        FROM BookingModel b
        WHERE b.room.id = r.id
         AND(
            :checkInDate < b.checkOutDate
            AND :checkOutDate > b.checkInDate
        )
    )
""")
    List<HotelModel> findAvailableHotelsByAddress(
            @Param("address") String address,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );
}

