package com.hotel.repo;

import com.hotel.models.AmenitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepo extends JpaRepository<AmenitiesModel,Long> {
}
