package com.matrangola.indicator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @RequestMapping("/makeCustomer")
    public String hello(@RequestParam String email) {
        return "hello " + email;
    }
}