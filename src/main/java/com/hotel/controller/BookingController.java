package com.hotel.controller;

import com.hotel.enums.BookingStatus;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.models.*;
import com.hotel.services.*;
import com.hotel.validator.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

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





    @GetMapping("/new")
    public String createNewBooking(Authentication authentication, Model model)
    {
        String username = authentication.getName();
        CustomerModel customer = customerServices.findByUsername(username);
         BookingModel booking = new BookingModel();
         //booking.setCustomer(new CustomerModel());
        // Auto set customer
        booking.setCustomer(customer);

        booking.setHotel(new HotelModel());
        booking.setRoom(new RoomModel());
       booking.setPayment(new PaymentModel());
        model.addAttribute("booking",booking);
//        model.addAttribute("customers", customerServices.findAllCustomer());
       model.addAttribute("hotels",hotelServices.findAllHotels());
        model.addAttribute("rooms",roomServices.findAllRooms());

        return "booking-form";
    }


    @PostMapping("/save")
    public String saveBooking(@ModelAttribute BookingModel booking,Authentication authentication, Model model) {



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
    public String fetchBookings(Authentication authentication,Model model)
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
    public List<RoomModel> getRoomsByHotel(@PathVariable Long hotelId)
    {
        return roomServices.findByHotelId(hotelId);
    }

    @GetMapping("/{hotelId}")
    public String bookingPage(

            @PathVariable Long hotelId,
            Authentication authentication,
            Model model
    )
    {

        String username = authentication.getName();

        CustomerModel customer = customerServices.findByUsername(username);

        HotelModel hotel = hotelServices.getHotelById(hotelId);

        BookingModel booking = new BookingModel();

        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(new RoomModel());

        booking.setPayment(new PaymentModel());

        model.addAttribute("booking", booking);

        return "hotel-booking";
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(
            @PathVariable Long id,
            Authentication authentication,
            Model model) {

        try {

            BookingModel booking =
                    bookingServices.getBookingById(id);

            booking.setStatus(BookingStatus.CANCELLED);

            bookingServices.saveBooking(booking);

            model.addAttribute("success",
                    "Booking cancelled successfully");

        } catch (Exception e) {

            model.addAttribute("error",
                    e.getMessage());
        }

        String username = authentication.getName();

        CustomerModel customer =
                customerServices.findByUsername(username);

        model.addAttribute("customer", customer);

        model.addAttribute("bookingList",
                bookingServices.findByCustomer(customer));

        return "customer-dashboard";
    }



}
