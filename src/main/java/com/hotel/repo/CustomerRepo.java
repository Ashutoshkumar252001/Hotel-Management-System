package com.hotel.repo;

import com.hotel.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo
        extends JpaRepository<CustomerModel, Long> {


    Optional<CustomerModel> findByUsername(String username);
}