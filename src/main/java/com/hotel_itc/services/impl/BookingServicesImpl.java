package com.hotel_itc.services.impl;


import com.hotel_itc.exception.BookingNotFoundException;
import com.hotel_itc.models.BookingModel;
import com.hotel_itc.repo.BookingRepo;
import com.hotel_itc.services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServicesImpl implements BookingServices {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public BookingModel getBookingById(Long id)
            throws BookingNotFoundException {

        Optional<BookingModel> opt = bookingRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new BookingNotFoundException(
                    "Booking not found for this ID: " + id);
        }
    }

    @Override
    public List<BookingModel> findAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public void saveBooking(BookingModel booking) {
        if (booking.getId() == null) {
            bookingRepo.save(booking);
        } else {
            Optional<BookingModel> opt = bookingRepo.findById(booking.getId());

            if (opt.isEmpty()){
                throw new BookingNotFoundException("booking not found by this id:"+booking.getId());
            }

                BookingModel b = opt.get();
                b.setCheckindate(booking.getCheckindate());
                b.setCheckoutdate(booking.getCheckoutdate());

                b.setStatus(booking.getStatus());

                bookingRepo.save(b);
            }
        }


        @Override
        public void deleteBooking (Long id){
            Optional<BookingModel> opt = bookingRepo.findById(id);
            if(opt.isEmpty()){
                throw new BookingNotFoundException("booking is not found for deletion for this id:"+id);
            }
            bookingRepo.deleteById(id);
        }
    }



