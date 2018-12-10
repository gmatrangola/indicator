package com.matrangola.indicator.controller;

import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
@RequestMapping(value = "/customers", produces = {"application/json"})
public class CustomerController {
    private static final SimpleDateFormat BIRTHDAY_TEXT_FORMAT = new SimpleDateFormat("YYYYMMdd");

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/new")
    public Customer makeCustomer(@RequestParam String email,
                               @RequestParam String lastName,
                               @RequestParam String firstName,
                                 @RequestParam(name = "birthday", required = false) String birthdayText) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setEmail(email);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        if (birthdayText != null) {
            try {
                customer.setBirthday(BIRTHDAY_TEXT_FORMAT.parse(birthdayText));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping(path = "/new", method = RequestMethod.PUT)
    public Customer add(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID());
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping(value={"/foo", "/foo/bar", "*.bar", "dove/*,**/data"})
    public String foo() {
        return "foo mapping success";
    }
}