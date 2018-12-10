package com.matrangola.indicator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @RequestMapping("/makeCustomer")
    public String hello() {
        return "hello world";
    }
}