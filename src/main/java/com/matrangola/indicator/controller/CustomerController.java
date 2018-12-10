package com.matrangola.indicator.controller;

import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/makeCustomer")
    public Customer makeCustomer(@RequestParam String email,
                               @RequestParam String lastName,
                               @RequestParam String firstName) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setEmail(email);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);

        customerRepository.save(customer);
        return customer;
    }
}