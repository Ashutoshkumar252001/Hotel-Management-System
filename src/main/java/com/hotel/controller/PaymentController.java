package com.hotel.controller;

import com.hotel.models.PaymentModel;
import com.hotel.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServices paymentServices;

    @GetMapping("/")
    public String getAllPayment(Model model) {
        List<PaymentModel> list = paymentServices.findAllPayment();
        if (list.isEmpty()) {
            model.addAttribute("error", "No Payment Found");
        } else {
            model.addAttribute("success", list.size() + " Payment found");
        }

        model.addAttribute("payments", list);

        return "payment";
    }

    @GetMapping("/find/{id}")
    public String getById(@PathVariable Long id, Model model) {
        try {
            PaymentModel paymentModel = paymentServices.getPaymentById(id);
            model.addAttribute("success","payment found ");
            model.addAttribute("payments",List.of(paymentModel));


        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            model.addAttribute("payments",null);

        }
        return "payment";


    }
}
