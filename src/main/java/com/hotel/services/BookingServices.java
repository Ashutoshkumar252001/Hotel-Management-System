package com.hotel.services;

import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.BookingModel;

import java.util.List;

public interface BookingServices {

    BookingModel getBookingById(Long id)
            throws BookingNotFoundException;

    List<BookingModel> findAllBookings();

    void saveBooking(BookingModel booking);


    void deleteBooking(Long id);
}
