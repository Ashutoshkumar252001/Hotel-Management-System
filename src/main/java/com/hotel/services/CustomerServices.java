package com.hotel.services;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.models.CustomerModel;

import java.util.List;

public interface CustomerServices {
CustomerModel findCustomerById(Long id) throws CustomerNotFoundException;
List<CustomerModel> findAllCustomer();
CustomerModel saveCustomer(CustomerModel customer)throws Exception;
void deleteCustomer(Long id);
    CustomerModel findByUsername(String username);


}
