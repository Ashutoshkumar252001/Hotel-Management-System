package com.hotel.repo;

import com.hotel.models.CustomerModel;
import com.hotel.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerModel,Long > {
    boolean existsByPhone(String phone);
    boolean existsByEmailIgnoreCase(String email);
}
