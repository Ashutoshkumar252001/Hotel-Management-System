package com.hotel.services.impl;

import com.hotel.models.AmenitiesModel;
import com.hotel.repo.AmenitiesRepo;
import com.hotel.services.AmenitiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AmenitiesServicesImpl implements AmenitiesServices {

    @Autowired
    private AmenitiesRepo amenitiesRepo;
    @Override
    public List<AmenitiesModel> findAllAmenities() {
        return amenitiesRepo.findAll() ;
    }
}
