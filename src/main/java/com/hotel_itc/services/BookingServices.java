package com.hotel_itc.services;

import com.hotel_itc.exception.BookingNotFoundException;
import com.hotel_itc.models.BookingModel;

import java.util.List;

public interface BookingServices {

    BookingModel getBookingById(Long id)
            throws BookingNotFoundException;

    List<BookingModel> findAllBookings();

    void saveBooking(BookingModel booking);


    void deleteBooking(Long id);
}
