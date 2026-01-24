package com.hotel_itc.controller;

import com.hotel_itc.exception.BookingNotFoundException;
import com.hotel_itc.models.BookingModel;
import com.hotel_itc.models.HotelModel;
import com.hotel_itc.models.RoomModel;
import com.hotel_itc.services.BookingServices;
import com.hotel_itc.services.CustomerServices;
import com.hotel_itc.services.HotelServices;
import com.hotel_itc.services.RoomServices;
import com.hotel_itc.validator.BookingDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
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

    @GetMapping("/")
    public String Home() {
        return "redirect:/booking/list";
    }

    @GetMapping("/booking/new")
    public String createNewBooking(Model model)
    {
        model.addAttribute("booking", new BookingModel());
        model.addAttribute("customers", customerServices.findAllCustomer());
        model.addAttribute("hotelMap",hotelServices.getHotelMap());




        return "booking-form";
    }

    @PostMapping("/booking/save")
    public String saveBooking(@ModelAttribute BookingModel booking, RedirectAttributes model) {

            if (booking.getId() == null)
            {
                 List<String> errors = bookingDataValidator.validate(booking);
                 if(!errors.isEmpty())
                 {
                     model.addFlashAttribute("error", errors);
                 }
                 else
                 {
                     try
                     {
                         bookingServices.saveBooking(booking);
                         model.addFlashAttribute("success", "Booking created successfully");
                     }
                     catch (Exception e)
                     {
                         model.addFlashAttribute("error", "Error during booking data creation");
                     }
                 }
            }
            model.addFlashAttribute("bookings",bookingServices.findAllBookings());
        return "redirect:/booking/list";
    }

    @GetMapping("/booking/edit/{id}")
    public String editBooking(@PathVariable Long id, Model model)
    {
        try
        {
            BookingModel booking =
                    bookingServices.getBookingById(id);
            model.addAttribute("booking", booking);
            return "booking-form";
        } catch (BookingNotFoundException e)
        {
            model.addAttribute("error", e.getMessage());
            return "redirect:/booking/list";
        }
    }

    @GetMapping("/booking/list")
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
    public String deleteBooking(@PathVariable Long id,
                                RedirectAttributes model)
    {
        try
        {
            bookingServices.deleteBooking(id);
            model.addFlashAttribute("success", "Booking deleted successfully");
        } catch (Exception e)
        {
            model.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/booking/list";
    }
}
