package com.hotel.services.impl;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.models.CustomerModel;
import com.hotel.repo.CustomerRepo;
import com.hotel.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerModel getCustomerById(Long id) throws CustomerNotFoundException {
        Optional<CustomerModel> opt = customerRepo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        else {
            throw new CustomerNotFoundException("Customer is Not Found for this ID:"+id);
        }
    }

    @Override
    public List<CustomerModel> findAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public void saveCustomer(CustomerModel customer){

       // Create // Update
        if(customer.getId()==null){
            customerRepo.save(customer);
        }
        else {
            Optional<CustomerModel> opt = customerRepo.findById(customer.getId());
            if(opt.isEmpty()){
                throw new CustomerNotFoundException("customer not fount by this id:"+customer.getId());
            }
            CustomerModel c= opt.get();
            c.setIdProofNumber(customer.getIdProofNumber());
            c.setId(customer.getId());
            c.setEmail(customer.getEmail());
            c.setName(customer.getName());
            customerRepo.save(c);
        }

    }



    @Override
    public void deleteCustomer(Long id) {
        Optional<CustomerModel>opt = customerRepo.findById(id);
        if(opt.isEmpty()){
            throw new CustomerNotFoundException("Customer not found by this ID for deletion :"+id);
        }
        customerRepo.deleteById(id);

    }
}
