package com.enigma.enigma_shop.service;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;

import java.util.List;


public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll(SearchCustomerRequest request);
    void updateStatusById(String id, Boolean status);
    CustomerResponse update(UpdateCustomerRequest request);
    void delete(String id);
}
