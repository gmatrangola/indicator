package com.matrangola.indicator.controller;

import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.repository.CustomerRepository;
import com.matrangola.indicator.validation.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/customers", produces = {"application/json"})
public class CustomerController {
    private static final SimpleDateFormat BIRTHDAY_TEXT_FORMAT = new SimpleDateFormat("YYYYMMdd");

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(path = "/")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @RequestMapping(path = "/customer/{id}")
    public Customer get(@PathVariable UUID id) {
        Optional<Customer> cust = customerRepository.findById(id);
        if (cust.isPresent()) return cust.get();
        else return new Customer(); // throw error for 402
    }

    @RequestMapping("/new")
    public Customer newCustomer(@RequestParam String email,
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
    public Customer newCustomer(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID());
        customerRepository.save(customer);
        return customer;
    }

    @RequestMapping(path = "/zipcode/{zipcode}", method = RequestMethod.GET)
    public List<Customer> zipcode(@PathVariable int zipcode) {
        return customerRepository.findCustomersByZipcode(zipcode);
    }

    @RequestMapping(path = "/customer")
    public Customer customerByEmail(@RequestParam String email) throws ResourceException {
        // todo 404 when not found
        Optional<Customer> result = customerRepository.findCustomerByEmail(email);
        if (result.isPresent()) return result.get();
        else throw new ResourceException(Customer.class, email);
    }

    @RequestMapping(path={"/foo", "/foo/bar", "*.bar", "dove/*,**/data"})
    public String foo() {
        return "foo mapping success";
    }
}