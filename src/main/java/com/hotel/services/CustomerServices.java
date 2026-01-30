package com.hotel.services;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.models.CustomerModel;

import java.util.List;

public interface CustomerServices {
CustomerModel getCustomerById(Long id) throws CustomerNotFoundException;
List<CustomerModel> findAllCustomer();
void saveCustomer(CustomerModel customer);
void deleteCustomer(Long id);
}
