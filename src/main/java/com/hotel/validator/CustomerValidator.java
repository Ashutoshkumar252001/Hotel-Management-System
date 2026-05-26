package com.hotel.validator;

import com.hotel.models.CustomerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerValidator implements DataValidator {

    @Override
    public List<String> validate(Object data) {

        List<String> errors = new ArrayList<>();

        CustomerModel customer = (CustomerModel) data;

        // ================= NAME VALIDATION =================

        if (customer.getName() == null
                || customer.getName().trim().isEmpty()) {

            errors.add("Name cannot be empty");
        }

        // ================= PHONE VALIDATION =================

        if (customer.getPhone() == null) {

            errors.add("Phone number cannot be null");

        } else if (customer.getPhone() < 1000000000L
                || customer.getPhone() > 9999999999L) {

            errors.add("Phone number must be exactly 10 digits");
        }

        // ================= ID PROOF VALIDATION =================

        if (customer.getIdProofNumber() == null
                || customer.getIdProofNumber().trim().isEmpty()) {

            errors.add("ID Proof Number cannot be empty");
        }

        // ================= EMAIL VALIDATION =================

        if (customer.getEmail() == null
                || customer.getEmail().trim().isEmpty()) {

            errors.add("Email cannot be empty");

        } else if (!customer.getEmail()
                .matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            errors.add("Invalid email format");
        }

        // ================= USERNAME VALIDATION =================

        if (customer.getUsername() == null
                || customer.getUsername().trim().isEmpty()) {

            errors.add("Username cannot be empty");

        } else if (customer.getUsername().length() < 4) {

            errors.add("Username must be at least 4 characters");
        }

        // ================= PASSWORD VALIDATION =================

        if (customer.getPassword() == null
                || customer.getPassword().trim().isEmpty()) {

            errors.add("Password cannot be empty");

        } else if (customer.getPassword().length() < 6) {

            errors.add("Password must be at least 6 characters");
        }

        // Role Validation
        if (customer.getRole() == null) {
            errors.add("Role must be selected");
        }

        return errors;
    }
    }

