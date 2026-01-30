package com.hotel.repo;

import com.hotel.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<HotelModel,Long> {
    boolean existsByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);
}

