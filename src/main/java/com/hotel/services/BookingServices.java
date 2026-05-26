package com.hotel.services;

import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.BookingModel;
import com.hotel.models.CustomerModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingServices {

    BookingModel getBookingById(Long id)
            throws BookingNotFoundException;

    List<BookingModel> findAllBookings();


    void saveBooking(BookingModel booking);


    void deleteBooking(Long id);
    List<BookingModel> findByCustomer(CustomerModel customer);

}
