package com.enigma.enigma_shop.service;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;

import java.util.List;


public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll(SearchCustomerRequest request);
    //List<Customer> getAll(String name, String mobilePhoneNo, Date birthDate, Boolean status);
    void updateStatusById(String id, Boolean status);
    Customer update(Customer customer);
    void delete(String id);
}
