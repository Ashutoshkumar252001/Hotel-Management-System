package com.hotel.controller;

import com.hotel.enums.Role;
import com.hotel.models.*;
import com.hotel.services.*;
import com.hotel.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private HotelServices hotelServices;

    @Autowired
    private RoomServices roomServices;

    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private CustomerValidator customerDataValidator;


    // ================= HOME PAGE =================

    @GetMapping("/")
    public String getHomePage(
            Authentication authentication,
            Model model) {

        // NOT LOGGED IN

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            return "redirect:/login";
        }

        Collection<? extends GrantedAuthority>
                authorities =
                authentication.getAuthorities();


        // ================= CUSTOMER =================

        if (authorities.stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_USER"))) {

            String username = authentication.getName();

            CustomerModel customer =
                    customerServices.findByUsername(username);

            if (customer == null) {

                model.addAttribute(
                        "error",
                        "Customer account not found");

                return "access-denied";
            }

            List<BookingModel> bookingList =
                    bookingServices.findByCustomer(customer);

            List<HotelModel> hotelList =
                    hotelServices.findAllHotels();

            List<RoomModel> roomList =
                    roomServices.findAllRooms();

            model.addAttribute("customer", customer);

            model.addAttribute(
                    "bookingList",
                    bookingList);

            model.addAttribute(
                    "hotelList",
                    hotelList);

            model.addAttribute(
                    "roomList",
                    roomList);

            return "customer-dashboard";
        }


        // ================= ADMIN =================

        if (authorities.stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_ADMIN"))) {

            String username =
                    authentication.getName();

            CustomerModel admin =
                    customerServices.findByUsername(username);

            model.addAttribute("customer", admin);

            List<PaymentModel> paymentList =
                    paymentServices.findAllPayment();

            double totalRevenue =
                    paymentList.stream()
                            .mapToDouble(PaymentModel::getAmount)
                            .sum();

            model.addAttribute(
                    "totalCustomers",
                    customerServices.findAllCustomer().size());

            model.addAttribute(
                    "totalHotels",
                    hotelServices.findAllHotels().size());

            model.addAttribute(
                    "totalRooms",
                    roomServices.findAllRooms().size());

            model.addAttribute(
                    "totalBookings",
                    bookingServices.findAllBookings().size());

            model.addAttribute(
                    "totalRevenue",
                    totalRevenue);

            return "admin-dashboard";
        }

        return "access-denied";
    }


    // ================= REGISTER PAGE =================

    @GetMapping("/register")
    public String getRegisterPage(Model model) {

        model.addAttribute(
                "customer",
                new CustomerModel());

        model.addAttribute(
                "roles",
                Role.values());

        return "register";
    }


    // ================= REGISTER USER =================

    @PostMapping("/register-user")
    public String registerUser(
            @ModelAttribute CustomerModel customer,
            Model model) {

        List<String> errors =
                customerDataValidator.validate(customer);

        if (!errors.isEmpty()) {

            model.addAttribute("error", errors);

            model.addAttribute(
                    "customer",
                    new CustomerModel());

            model.addAttribute(
                    "roles",
                    Role.values());

            return "customer-form";
        }

        try {

            customerServices.saveCustomer(customer);

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage());

            model.addAttribute(
                    "customer",
                    new CustomerModel());

            model.addAttribute(
                    "roles",
                    Role.values());

            return "customer-form";
        }

        model.addAttribute(
                "success",
                "Registration Successful");

        return "login";
    }


    // ================= LOGIN PAGE =================

    @GetMapping("/login")
    public String getLoginPage(
            @RequestParam(
                    value = "error",
                    required = false)
            String error,

            @RequestParam(
                    value = "logout",
                    required = false)
            String logout,

            Model model) {

        if (error != null) {

            model.addAttribute(
                    "error",
                    "Invalid Username or Password");
        }

        if (logout != null) {

            model.addAttribute(
                    "success",
                    "Logout Successful");
        }

        return "login";
    }


    // ================= ACCESS DENIED =================

    @GetMapping("/access-denied")
    public String accessDenied() {

        return "access-denied";
    }


    // ================= EDIT PROFILE =================

    @GetMapping("/profile/edit/{id}")
    public String editProfile(
            @PathVariable Long id,
            Authentication authentication,
            Model model) {

        try {

            if (authentication == null ||
                    !authentication.isAuthenticated()) {

                return "redirect:/login";
            }

            CustomerModel customer =
                    customerServices.findCustomerById(id);

            if (customer == null) {

                return "access-denied";
            }

            String loggedInUsername =
                    authentication.getName();

            // SECURITY CHECK

            if (customer.getUsername() == null ||
                    !customer.getUsername()
                            .equals(loggedInUsername)) {

                return "access-denied";
            }

            model.addAttribute("customer", customer);

            model.addAttribute(
                    "roles",
                    Role.values());

            return "customer-form";

        } catch (Exception e) {

            return "access-denied";
        }
    }


    @GetMapping("/available-hotels")
    public String findAvailableHotelsByAddress(
            @RequestParam String address,

            @RequestParam String startDate,
            @RequestParam String endDate,
            Model model
    ) {


        LocalDate from =
                LocalDate.parse(startDate);

        LocalDate to =
                LocalDate.parse(endDate);

        System.out.println("Address = " + address);
        System.out.println("From = " + from);
        System.out.println("To = " + to);

        List<HotelModel> hotels =
                hotelServices.findAvailableHotelsByAddress(
                        address,
                        from,
                        to
                );
        System.out.println("Hotels Found = " + hotels.size());

        for (HotelModel h : hotels) {

            System.out.println(h.getName());
        }

        model.addAttribute("hotels", hotels);

        return "available-hotels";
    }


}