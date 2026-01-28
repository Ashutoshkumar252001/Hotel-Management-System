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


    @GetMapping("/new")
    public String createNewHotel(Model model)
    {
        model.addAttribute("hotel", new HotelModel());
        return "hotel-form";
    }

    @PostMapping("/save")
    public String saveHotel(@ModelAttribute HotelModel hotel, Model model)
    {
        List<String> errors = hotelDataValidator.validate(hotel);


        if(!errors.isEmpty())
        {
            model.addAttribute("error", errors);
            return "hotel-form";
        }


        try
        {
            hotelServices.saveHotel(hotel);


            model.addAttribute("success", "Hotel created successfully");
        }
        catch (Exception e)
        {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("hotels", hotelServices.findAllHotels());
        return "hotel";
    }



    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable Long id,
                            Model model)
    {

        try
        {
            HotelModel hotel = hotelServices.getHotelById(id);
            model.addAttribute("hotel", hotel);
            model.addAttribute("rooms", roomServices.findAllRooms());
            return "hotel-form";

        }
        catch (HotelNotFoundException e)
        {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("hotels", hotelServices.findAllHotels());
        return "hotel";
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
    public String deleteHotel(@PathVariable Long id, Model model) {

        try {
            hotelServices.deleteHotel(id);
            model.addAttribute("success", "Hotel deleted successfully");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("hotels", hotelServices.findAllHotels());
        return "hotel";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable Long id,Model model){
        try{
            HotelModel hotel = hotelServices.getHotelById(id);
            model.addAttribute("sucess","hotel found");
            model.addAttribute("hotels",List.of(hotel));
        }catch (Exception e){
            model.addAttribute("error","hotel not found");
            model.addAttribute("hotels",null);
        }
        return "hotel";
    }

}
