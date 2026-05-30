package com.hotel.services.impl;


import com.hotel.enums.BookingStatus;
import com.hotel.enums.PaymentMode;
import com.hotel.enums.PaymentStatus;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.*;
import com.hotel.repo.BookingRepo;
import com.hotel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServicesImpl implements BookingServices {

    @Autowired
    private BookingRepo bookingRepo;




    @Autowired
    private PaymentServices paymentServices;
    @Autowired
    private HotelServices hotelServices;
    @Autowired
    private RoomServices roomServices;
    @Autowired
    private CustomerServices customerServices;




    @Override
    public BookingModel getBookingById(Long id)
            throws BookingNotFoundException {

        Optional<BookingModel> opt = bookingRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        else
        {
            throw new BookingNotFoundException("Booking not found for this ID: " + id);
        }
    }

    @Override
    public List<BookingModel> findAllBookings() {
        return bookingRepo.findAll();
    }




    @Override
    public void saveBooking(BookingModel booking) {
//        booking.setHotel(hotelServices.getHotelById(booking.getHotel().getId()));
//        booking.setRoom(roomServices.getRoomById(booking.getRoom().getId()));
//        booking.setCustomer(customerServices.getCustomerById(booking.getCustomer().getId()));



        if (booking.getId() == null) {
            booking.setHotel(hotelServices.getHotelById(booking.getHotel().getId()));
            booking.setRoom(roomServices.getRoomById(booking.getRoom().getId()));
            booking.setCustomer(customerServices.findCustomerById(booking.getCustomer().getId()));
            booking.setStatus(BookingStatus.CONFIRMED);





            PaymentModel payment = new PaymentModel();

            payment.setPaymentMode(booking.getPayment().getPaymentMode());
            payment.setPaymentStatus(PaymentStatus.PAID);
            Long days = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
            payment.setAmount(days*booking.getRoom().getPricePerNight());

            payment.setBooking(booking);
            booking.setPayment(payment);

            bookingRepo.save(booking);
        } else {
            Optional<BookingModel> bookingOpt = bookingRepo.findById(booking.getId());


            if (bookingOpt.isEmpty()){
                throw new BookingNotFoundException("booking not found by this id:"+booking.getId());
            }

                BookingModel b = bookingOpt.get();
            b.setHotel(hotelServices.getHotelById(booking.getHotel().getId()));
            b.setRoom(roomServices.getRoomById(booking.getRoom().getId()));
            b.setCustomer(customerServices.findCustomerById(booking.getCustomer().getId()));

            Long days = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());



            PaymentModel paymentModel = b.getPayment();


            paymentModel.setAmount(days * booking.getRoom().getPricePerNight());
            paymentModel.setPaymentMode(b.getPayment().getPaymentMode());
            paymentModel.setPaymentStatus(PaymentStatus.PAID);


            b.setCheckInDate(booking.getCheckInDate());
                b.setCheckOutDate(booking.getCheckOutDate());
                b.setStatus(booking.getStatus());
                b.setPayment(paymentModel);



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

    @Override
    public List<BookingModel> findByCustomer(CustomerModel customer) {

        return bookingRepo.findByCustomer(customer);
    }



    }



