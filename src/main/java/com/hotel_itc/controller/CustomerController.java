package com.hotel_itc.controller;

import com.hotel_itc.exception.CustomerNotFoundException;
import com.hotel_itc.models.CustomerModel;
import com.hotel_itc.services.CustomerServices;
import com.hotel_itc.validator.CustomerDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController
{


    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private CustomerDataValidator customerDataValidator;
    @GetMapping("/")
    public String displayCustomerName(Model model)
    {
        return "redirect:/customer/list";
    }

    @GetMapping("/new")
    public String createNewCustomer(Model model)
    {
        model.addAttribute("customer",new CustomerModel());
        return "customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute CustomerModel c, RedirectAttributes model){

        if(c.getId()==null)
        {
            List<String> errors = customerDataValidator.validate(c);
            if (!errors.isEmpty())
            {
                model.addFlashAttribute("error", errors);
            }
            else
            {
                try
                {
                    customerServices.saveCustomer(c);
                    model.addFlashAttribute("success", "Customer created successfully");
                } catch (Exception e)
                {
                    model.addFlashAttribute("error", "Error during Customer data creation");
                }
            }
        }
        model.addFlashAttribute("customer",customerServices.findAllCustomer());

        return "redirect:/customer/list";

    }
    @GetMapping("/edit/{id}")
    public String getCustomerById(@PathVariable Long id, Model model)
    {
        try
        {
            CustomerModel c1 = customerServices.getCustomerById(id);
            model.addAttribute("customer",c1);
            model.addAttribute("success","customer found");
            return "customer-form";
        }
        catch (CustomerNotFoundException e)
        {
            model.addAttribute("customer",customerServices.findAllCustomer());
            model.addAttribute("error","No customer found for with given ID:"+id);
        }
        return "customer";
    }

    @GetMapping("/list")
    public String fetchCustomer(Model model)
    {
        List<CustomerModel> customer = customerServices.findAllCustomer();
        if(customer.isEmpty())
        {
            model.addAttribute("error","no customer found");
        }
        else
        {
            model.addAttribute("success",customer.size()+"customer found");

        }
        model.addAttribute("customers",customer);
        return "customer";
    }

    @DeleteMapping("/delete/{id}")
    public String removeCustomerById(@PathVariable Long id,RedirectAttributes model)
    {
        try
        {
            customerServices.deleteCustomer(id);
            model.addFlashAttribute("success","customer deleted successfully");
        }
        catch (Exception e)
        {
            model.addFlashAttribute("error",e.getMessage());
        }
        return "redirect:/customer/list";
    }

}

