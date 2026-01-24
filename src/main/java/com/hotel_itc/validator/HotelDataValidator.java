package com.hotel_itc.validator;

import com.hotel_itc.models.HotelModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotelDataValidator implements DataValidator {

    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        HotelModel hotel = (HotelModel) data;


       // }
        return errors;
    }
}
