package com.hotel_itc.controller;

import com.hotel_itc.exception.RoomNotFoundException;
import com.hotel_itc.models.RoomModel;
import com.hotel_itc.services.RoomServices;
import com.hotel_itc.services.HotelServices;
import com.hotel_itc.validator.RoomDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private HotelServices hotelServices;

    @Autowired
    private RoomDataValidator roomDataValidator;

    @GetMapping("/")
    public String roomHome()
    {
        return "redirect:/room/list";
    }

    @GetMapping("/new")
    public String createNewRoom(Model model)
    {
        model.addAttribute("room", new RoomModel());
        model.addAttribute("hotels", hotelServices.findAllHotels());
        return "room-form";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute RoomModel room, RedirectAttributes model)
    {

            if (room.getId() == null)
            {
                List<String > errors = roomDataValidator.validate(room);
                if(!errors.isEmpty())
                {
                    model.addFlashAttribute("error", errors);
                }
                else
                {
                    try
                    {
                        roomServices.saveRoom(room);
                        model.addFlashAttribute("success", "Room created successfully");
                    }
                    catch (Exception e)
                    {
                        model.addFlashAttribute("error", "error during room data creation");
                    }
                }
            }
            model.addFlashAttribute("rooms",roomServices.findAllRooms());

        return "redirect:/room/list";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model)
    {
        try
        {
            RoomModel room = roomServices.getRoomById(id);
            model.addAttribute("room", room);
            model.addAttribute("hotels", hotelServices.findAllHotels());
            return "room-form";
        }
        catch (RoomNotFoundException e)
        {
            model.addAttribute("error", e.getMessage());
            return "redirect:/room/list";
        }
    }

    @GetMapping("/list")
    public String fetchRooms(Model model)
    {
        List<RoomModel> rooms = roomServices.findAllRooms();

        if (rooms.isEmpty())
        {
            model.addAttribute("error", "No rooms found");
        }
        else
        {
            model.addAttribute("success",
                    rooms.size() + " rooms found");
        }

        model.addAttribute("rooms", rooms);
        return "room";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id,
                             RedirectAttributes model)
    {
        try
        {
            roomServices.deleteRoom(id);
            model.addFlashAttribute(
                    "success", "Room deleted successfully");
        }
        catch (Exception e)
        {
            model.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/room/list";
    }
}

