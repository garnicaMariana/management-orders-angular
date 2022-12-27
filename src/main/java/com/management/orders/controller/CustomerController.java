package com.management.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.orders.model.Customer;
import com.management.orders.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("getCustomer/{name}")
	public ResponseEntity<Customer> getCustomerByName(@PathVariable String name){
		Customer customer = customerRepository.findByNumCustomer(name);
		return ResponseEntity.ok(customer);
	}

}
