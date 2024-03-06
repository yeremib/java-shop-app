package com.enigma.enigma_shop.service.impl;

import com.enigma.enigma_shop.constant.ResponseMessage;
import com.enigma.enigma_shop.constant.UserRole;
import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.entity.Role;
import com.enigma.enigma_shop.entity.UserAccount;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        /*
        * 3 Object Criteria yang digunakan
        * 1. Criteria Builder -> untuk membangun query expression (<, >, like, <>, !=) dan membangun query, update, delete
        * 2. Criteria (Query, Update, Delete)
        * 3. Root -> merepresentasikan entity(table)
        * */

        // SELECT * FROM m_customer
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // SELECT
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

        // FROM m_customer
        Root<Customer> root = cq.from(Customer.class);

        // SELECT * FROM m_customer
        cq.select(root);
        // SELECT * FROM m_customer WHERE name LIKE %name%

//        cq.where(
//                cb.like(root.get("name"), "%" + "yere" + "%")
//        );

//        cq.where(
//            cb.or(// defaultnya and
//                    cb.like(cb.lower(root.get("name")), "%" + "yere".toLowerCase() + "%"),
//                    cb.equal(root.get("mobilePhoneNo"), "098857588")
//                )
//        );

        // Query Param
//        String name = "Yeremi";
//        String phone = "098857588";
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (name != null) {
//            Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
//            predicates.add(namePredicate);
//        }
//
//        if(phone != null) {
//            Predicate phonePredicate = cb.equal(root.get("mobilePhoneNo"), phone);
//            predicates.add(phonePredicate);
//        }
//
//        cq.where(cb.or(predicates.toArray(new Predicate[]{})));
//
//        // SELECT * FROM m_customer
//        List<Customer> result = entityManager.createQuery(cq).getResultList();
//
//        return result;

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


//    @Override
//    public List<Customer> getAll(String name, String mobilePhoneNo, Date birthDate, Boolean status) {
//        if(name != null || mobilePhoneNo != null || birthDate != null || status != null) return customerRepository
//                .findAllByNameLikeOrMobilePhoneNoOrBirthdateOrStatus(
//                "%" + name + "%", mobilePhoneNo, birthDate, status
//                );
//        return customerRepository.findAll();
//    }



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
