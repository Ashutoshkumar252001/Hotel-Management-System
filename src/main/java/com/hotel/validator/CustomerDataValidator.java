package com.hotel.validator;

import com.hotel.models.CustomerModel;
import com.hotel.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataValidator implements DataValidator {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<String> validate(Object data) {

        List<String> errors = new ArrayList<>();
        CustomerModel customer = (CustomerModel) data;

        if (customer.getPhone() == null || !customer.getPhone().matches("\\d{10}")) {
            errors.add("Phone number must be 10 digits");
        } else if (customer.getId() == null &&
                customerRepo.existsByPhone(customer.getPhone())) {
            errors.add("Phone number already exists");
        }


        return errors;
    }
}