package com.hotel_itc.validator;

import com.hotel_itc.models.CustomerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataValidator implements DataValidator{
    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        CustomerModel customer = (CustomerModel) data;
        if(customer.getPhone().length()>10){
            errors.add("phone no not greater than 10 digit");
        }
        if (customer.getPhone() == null || customer.getPhone().isBlank()) {
             errors.add("phone number is required");
        }

        if (!customer.getPhone().matches("\\d{10}")) {
             errors.add("phone number must be 10 digits");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            errors.add("Email is required");
        }

        if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Invalid email format");
        }

        return errors;
    }
}
