package com.hotel_itc.validator;

import com.hotel_itc.models.HotelModel;
import com.hotel_itc.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class
HotelDataValidator implements DataValidator {

    @Autowired
    HotelRepo hotelRepo;

    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        HotelModel hotel = (HotelModel) data;

        if (hotel.getName() == null || hotel.getAddress() == null) {
            errors.add("Hotel name and address are required");
            return errors;


        }
        return errors;
        }
    }
