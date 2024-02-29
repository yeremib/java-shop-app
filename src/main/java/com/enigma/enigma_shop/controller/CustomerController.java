package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.constant.APIUrl;
import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer( @RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "mobilePhoneNo", required = false) String phoneNumber,
                                          @RequestParam(name = "birthDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd") String birthDate,
                                          @RequestParam(name = "status", required = false) Boolean status
    ) {
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .mobilePhoneNumber(phoneNumber)
                .birthDate(birthDate)
                .status(status)
                .build();

        List<Customer> customers = customerService.getAll(request);
        return ResponseEntity.ok(customers);
    }

//    @GetMapping
//    public List<Customer> getAllCustomer(
//            @RequestParam(name = "name", required = false) String name,
//            @RequestParam(name = "mobilePhoneNo", required = false) String mobilePhoneNo,
//            @RequestParam(name = "birthdate", required = false) Date birthDate,
//            @RequestParam(name = "status", required = false) Boolean status
//    ) {
//        return customerService.getAll(name, mobilePhoneNo, birthDate, status);
//    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.update(customer);
        return ResponseEntity.ok(customer1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatusCustomer(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "status") Boolean status
    ) {
        customerService.updateStatusById(id, status);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
