package com.enigma.enigma_shop.service.impl;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.repository.CustomerRepository;
import com.enigma.enigma_shop.service.CustomerService;
import com.enigma.enigma_shop.service.UserService;
import com.enigma.enigma_shop.specification.CustomerSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final EntityManager entityManager;
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) throw new RuntimeException("customer not found");
        return customer.get();
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest request) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root);
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll(specification);
    }

    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id, status);
    }

    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }


    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer currentCustomer = findByIdOrThrowNotFound(request.getId());

        currentCustomer.setName(request.getName());
        currentCustomer.setMobilePhoneNo(request.getMobilePhoneNo());
        currentCustomer.setAddress(request.getAddress());
        currentCustomer.setBirthdate(Date.valueOf(request.getBirthDate()));


        customerRepository.saveAndFlush(currentCustomer);
        return convertCustomerToCustomerResponse(currentCustomer);

    }

    @Override
    public void delete(String id) {
        Customer currCustomer = getById(id);
        customerRepository.delete(currCustomer);
    }

    private CustomerResponse convertCustomerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobilePhoneNo(customer.getMobilePhoneNo())
                .address(customer.getAddress())
                .status(customer.getStatus())
                .userAccountId(customer.getUserAccount().getId())
                .build();
    }

}
