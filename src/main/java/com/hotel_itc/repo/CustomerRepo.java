package com.hotel_itc.repo;

import com.hotel_itc.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerModel,Long > {
}
