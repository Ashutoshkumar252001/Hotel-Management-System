package com.hotel.auth;

import com.hotel.enums.Role;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.models.CustomerModel;
import com.hotel.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailServices implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerModel> customerOpt = customerRepo.findByUsername(username);
        if(customerOpt.isEmpty()){
            throw new CustomerNotFoundException("User not found with username: " + username);
        }

        CustomerModel customer = customerOpt.get();
        Role role = customer.getRole();

        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .roles(role.name())
                .build();

    }
}

