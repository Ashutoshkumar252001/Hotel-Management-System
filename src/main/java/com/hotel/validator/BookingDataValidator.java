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
        BookingModel booking = (BookingModel) data;
        if(booking.getCheckInDate().isBefore(LocalDate.now())){
            errors.add("booking data cannot older than today");

        }
        if (booking.getCheckOutDate().isBefore(booking.getCheckInDate() ) || booking.getCheckOutDate().isEqual(booking.getCheckInDate())){
            errors.add("Check-out date must be after check in date");
        }
        return List.of();
    }
}
