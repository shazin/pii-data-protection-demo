package com.medium.shazinsadakath.pii.data.protection.demo.controller;

import com.medium.shazinsadakath.pii.data.protection.demo.dto.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> getCustomers() {
        return Arrays.asList(new Customer("1234567890", "James", "Cordon"),
                new Customer("0987654321", "Abraham", "Lincoln"));
    }

}
