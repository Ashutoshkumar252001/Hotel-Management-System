package com.hotel.repo;

import com.hotel.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HotelRepo extends JpaRepository<HotelModel,Long> {
    boolean existsByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);
    List<HotelModel> findByAddressContainingIgnoreCase(String address);
}

