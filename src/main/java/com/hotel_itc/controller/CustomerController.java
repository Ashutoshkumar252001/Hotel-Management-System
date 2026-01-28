package com.hotel_itc.controller;

import com.hotel_itc.exception.CustomerNotFoundException;
import com.hotel_itc.models.CustomerModel;
import com.hotel_itc.models.PaymentModel;
import com.hotel_itc.services.CustomerServices;
import com.hotel_itc.validator.CustomerDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController
{


    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private CustomerDataValidator customerDataValidator;


    @GetMapping("/new")
    public String createNewCustomer(Model model)
    {
        model.addAttribute("customer",new CustomerModel());
        return "customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute CustomerModel c, Model model)


        {
            List<String> errors = customerDataValidator.validate(c);
            if (!errors.isEmpty())
            {
                model.addAttribute("error", errors.get(0));
                return "customer.html";
            }
            else
            {
                try
                {
                    customerServices.saveCustomer(c);
                    model.addAttribute("success", "Customer created successfully");
                } catch (Exception e)
                {
                    model.addAttribute("error", "Error during Customer data creation");
                }
            }

        model.addAttribute("customers",customerServices.findAllCustomer());
        return "customer";

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
    public String removeCustomerById(@PathVariable Long id,Model model)
    {
        try
        {
            customerServices.deleteCustomer(id);
            model.addAttribute("success","customer deleted successfully");
        }
        catch (Exception e)
        {
            model.addAttribute("error",e.getMessage());
        }
        model.addAttribute("customers",customerServices.findAllCustomer());
        return "customer";
    }
    @GetMapping("/find/{id}")
    public String getById(@PathVariable Long id, Model model) {
        try {
            CustomerModel customer = customerServices.getCustomerById(id);
            model.addAttribute("success","payment found ");
            model.addAttribute("customers",List.of(customer));


        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            model.addAttribute("customers",null);

        }
        return "customer";


    }

}

