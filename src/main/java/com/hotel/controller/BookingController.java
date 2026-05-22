package com.hotel.controller;

import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.*;
import com.hotel.services.*;
import com.hotel.validator.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController
{

    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private HotelServices hotelServices;

    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private BookingDataValidator bookingDataValidator;

//    @Autowired
//    private PaymentServices paymentServices;


    @GetMapping("/new")
    public String createNewBooking(Model model)
    {
        BookingModel booking = new BookingModel();
        booking.setPayment(new PaymentModel());
        model.addAttribute("booking",booking);
        model.addAttribute("customers", customerServices.findAllCustomer());
        model.addAttribute("hotels",hotelServices.findAllHotels());
        model.addAttribute("rooms",roomServices.findAllRooms());

        return "booking-form";
    }



    @PostMapping("/save")
    public String saveBooking(@ModelAttribute BookingModel booking, Model model) {
        if(booking.getId()==null) {
            List<String> errors = bookingDataValidator.validate(booking);
            if (!errors.isEmpty()) {
                model.addAttribute("error", errors);

                return "booking-form";
            } else {
                try {
                    bookingServices.saveBooking(booking);
                    model.addAttribute("success", "Booking created successfully");
                } catch (Exception e) {
                    model.addAttribute("error", e.getMessage());
                }
            }
        }
        else{
         try{
             bookingServices.saveBooking(booking);
             model.addAttribute("success","Booking updated successfully");
         }catch (Exception e){
             model.addAttribute("error","error during booking update");
         }
         }

        model.addAttribute("bookings", bookingServices.findAllBookings());
        return "booking";
    }

    @GetMapping("/edit/{id}")
    public String editBooking(@PathVariable Long id, Model model)
    {
        try
        {

            BookingModel booking = bookingServices.getBookingById(id);

            model.addAttribute("booking", booking);
            model.addAttribute("customers", customerServices.findAllCustomer());
            model.addAttribute("hotels",hotelServices.findAllHotels());
            model.addAttribute("rooms",roomServices.findAllRooms());
            return "booking-form";
        } catch (BookingNotFoundException e)
        {
            model.addAttribute("error", "No Booking Found");
            model.addAttribute("bookings",bookingServices.findAllBookings());
        }
        return "booking" ;
    }

    @GetMapping("/list")
    public String fetchBookings(Model model)
    {
        List<BookingModel> booking =
                bookingServices.findAllBookings();

        if (booking.isEmpty())
        {
            model.addAttribute("error", "No bookings found");
        } else
        {
            model.addAttribute("success", booking.size() + " bookings found");
        }

        model.addAttribute("bookings", booking);
        return "booking";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, Model model)
    {
        try
        {
            bookingServices.deleteBooking(id);
            model.addAttribute("success", "Booking deleted successfully");
        } catch (Exception e)
        {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("bookings", bookingServices.findAllBookings());
        return "booking";
    }
    @GetMapping("/find/{id}")
    public String findById(@PathVariable Long id,Model model){
        try {
            BookingModel booking = bookingServices.getBookingById(id);
            model.addAttribute("sucess","booking found");
            model.addAttribute("bookings",List.of(booking));
        }catch (Exception e){
            model.addAttribute("error","booking not found");
            model.addAttribute("bookings",null);
        }
        return "booking";
    }







    @GetMapping("/rooms/by-hotel/{hotelId}")
    @ResponseBody
    public List<RoomModel> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomServices.findByHotelId(hotelId);
    }
}
