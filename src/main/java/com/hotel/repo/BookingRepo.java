package com.hotel.repo;

import com.hotel.models.BookingModel;
import com.hotel.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepo extends JpaRepository<BookingModel,Long> {
    List<BookingModel> findByCustomer(CustomerModel customer);
}
