package com.hotel_itc.controller;

import com.hotel_itc.exception.HotelNotFoundException;
import com.hotel_itc.models.HotelModel;
import com.hotel_itc.services.HotelServices;
import com.hotel_itc.services.RoomServices;
import com.hotel_itc.validator.HotelDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/hotel")
public class HotelController
{

    @Autowired
    private HotelServices hotelServices;

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private HotelDataValidator hotelDataValidator;

    @GetMapping("/")
    public String hotelHome()
    {
        return "redirect:/hotel/list";
    }

    @GetMapping("/new")
    public String createNewHotel(Model model)
    {
        model.addAttribute("hotel", new HotelModel());
        return "hotel-form";
    }

    @PostMapping("/save")
    public String saveHotel(@ModelAttribute HotelModel hotel, RedirectAttributes model)
    {
        if(hotel.getId()==null)
        {
            List<String> errors = hotelDataValidator.validate(hotel);
            if(!errors.isEmpty())
            {
                model.addFlashAttribute("error",errors);
            }
            else
            {
                try
                {
                    hotelServices.saveHotel(hotel);
                    model.addFlashAttribute("success","Hotel created successfully");
                }
                catch (Exception e)
                {
                    model.addFlashAttribute("errors","Error during Hotel  data Creation");
                }
            }
        }

        model.addFlashAttribute("hotel",hotelServices.findAllHotels());

         return "redirect:/hotel/list";
    }

    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable Long id,
                            RedirectAttributes model)
    {

        try
        {
            HotelModel hotel = hotelServices.getHotelById(id);
            model.addFlashAttribute("hotel", hotel);
            model.addFlashAttribute("rooms", roomServices.findAllRooms());
            return "hotel-form";

        }
        catch (HotelNotFoundException e)
        {
            model.addFlashAttribute("error", e.getMessage());
            return "redirect:/hotel/list";
        }
    }

    @GetMapping("/list")
    public String fetchHotels(Model model)
    {

        List<HotelModel> hotel = hotelServices.findAllHotels();

        if (hotel.isEmpty())
        {
            model.addAttribute("error", "No hotels found");
        }
        else
        {
            model.addAttribute("success", hotel.size() + " hotels found");
        }

        model.addAttribute("hotels", hotel);
        return "hotel";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id, RedirectAttributes redirect)
    {

        try
        {
            hotelServices.deleteHotel(id);
            redirect.addFlashAttribute("success", "Hotel deleted successfully");
        }
        catch (Exception e)
        {
            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/hotel/list";
    }
}
