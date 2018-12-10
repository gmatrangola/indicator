package com.matrangola.indicator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {
    @RequestMapping("/makeCustomer")
    public String hello(@RequestParam Optional<String> email) {
        if (email.isPresent()) return "hello " + email.get();
        else return "hello everyone!";
    }
}