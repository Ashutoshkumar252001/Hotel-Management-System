package com.hotel.controller;

import com.hotel.enums.Role;
import com.hotel.exception.UserNotCreatedException;
import com.hotel.models.HotelModel;
import com.hotel.models.UserModel;
import com.hotel.services.HotelServices;
import com.hotel.services.UserService;
import com.hotel.validator.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoValidator userInfoValidator;

    @Autowired
    private HotelServices hotelServices;

    @GetMapping("/")
    public String getHome(Model model){
        List<HotelModel> hotels = hotelServices.findAllHotels();
        model.addAttribute("hotels",hotels);
        return "home";
    }




    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new UserModel());
        model.addAttribute("availableRoles", Role.getAllRoleNames());
        return "signup";  // signup.html
    }




    @PostMapping("/user-create")
    public String registerUser(Model model,
                               @ModelAttribute UserModel userModel,
                               @RequestParam(value = "selectedRoles", required = false) List<String> selectedRoles){

        List<String> errors =  userInfoValidator.validate(userModel);
        if(!errors.isEmpty()) {
            model.addAttribute("error", errors);
            model.addAttribute("user", new UserModel());
            model.addAttribute("availableRoles", Role.getAllRoleNames());
            return "signup";
        }

        // Join selected roles into comma-separated string, default to USER if none selected
        if (selectedRoles == null || selectedRoles.isEmpty()) {
            userModel.setRoles(Role.USER.getRoleName());
        } else {
            userModel.setRoles(String.join(",", selectedRoles));
        }

        try {
            userService.registerUser(userModel);
        } catch (UserNotCreatedException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new UserModel());
            model.addAttribute("availableRoles", Role.getAllRoleNames());
            return "signup";
        }
        model.addAttribute("success", "User with username " + userModel.getUserName() + " registered successfully");
        model.addAttribute("user", new UserModel());
        return "login";  // login.html
    }


    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout){
        if(error != null){
            model.addAttribute("error","Invalid username OR password");
        }
        if(logout != null){
            model.addAttribute("success", "You have been logged out successfully");
        }

        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }


}
