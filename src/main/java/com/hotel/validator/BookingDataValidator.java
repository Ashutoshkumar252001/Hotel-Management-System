package com.hotel.validator;

import com.hotel.models.BookingModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        List<String> errors= new ArrayList<>();
        LocalDate today = LocalDate.now();
        BookingModel booking = (BookingModel) data;
        if(booking.getCheckInDate().isBefore(LocalDate.now())){
            errors.add("booking data cannot older than today");

        }
        if (booking.getCheckOutDate()==null ||!booking.getCheckOutDate().isAfter(booking.getCheckInDate() ) ){
            errors.add("Check-out date must be after check in date");
        }
        if(booking.getCheckInDate()==null || booking.getCheckInDate().isBefore(today))
            errors.add("booking date cannot be before today");
        return errors;
    }
}
