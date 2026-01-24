package com.hotel_itc.repo;

import com.hotel_itc.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<HotelModel,Long> {
}
