package com.hotel.services;

import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.BookingModel;
import com.hotel.models.CustomerModel;

import java.util.List;

public interface BookingServices {

    BookingModel getBookingById(Long id)
            throws BookingNotFoundException;

    List<BookingModel> findAllBookings();
    List<BookingModel> findBookingsByCustomer(String username);


    void saveBooking(BookingModel booking);


    void deleteBooking(Long id);
}
