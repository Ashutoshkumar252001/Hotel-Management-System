package com.hotel_itc.services;

import com.hotel_itc.exception.CustomerNotFoundException;
import com.hotel_itc.models.CustomerModel;

import java.util.List;

public interface CustomerServices {
CustomerModel getCustomerById(Long id) throws CustomerNotFoundException;
List<CustomerModel> findAllCustomer();
void saveCustomer(CustomerModel customer);
void deleteCustomer(Long id);
}
