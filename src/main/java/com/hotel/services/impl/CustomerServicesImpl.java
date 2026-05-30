package com.hotel.services.impl;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.models.CustomerModel;
import com.hotel.repo.CustomerRepo;
import com.hotel.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerModel findCustomerById(Long id) throws CustomerNotFoundException
    {
        Optional<CustomerModel> opt = customerRepo.findById(id);
        if(opt.isPresent())
        {
            return opt.get();
        }
        else
        {
            throw new CustomerNotFoundException("Customer is Not Found for this ID:"+id);
        }
    }

    @Override
    public List<CustomerModel> findAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public CustomerModel saveCustomer(CustomerModel customer)throws Exception{
       try {
           customer.setName(customer.getName().toUpperCase());

       // Create // Update
        if(customer.getId()==null){
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
          return  customerRepo.save(customer);

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
            c.setPhone(customer.getPhone());
            c.setUsername(customer.getUsername());
            c.setRole(customer.getRole());
            if(customer.getPassword() != null && !customer.getPassword().trim().isEmpty()) {
                c.setPassword(passwordEncoder.encode(customer.getPassword()));
            }
            return customerRepo.save(c);
        }

    }
       catch(CustomerNotFoundException e) {
           throw e;
       }
       catch (Exception e) {
           throw new Exception("Error while saving the data. " + "Please check the given data.");
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

    @Override
    public CustomerModel findByUsername(String username) {
        Optional<CustomerModel> customer = customerRepo.findByUsername(username);
        if(customer.isPresent()){
            return customer.get();
        }
        return null;
    }



}
