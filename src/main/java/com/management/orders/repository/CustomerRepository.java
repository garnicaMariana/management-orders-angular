package com.management.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.orders.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByNumCustomer(String numCustomer);


}
