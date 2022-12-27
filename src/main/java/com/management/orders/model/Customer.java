package com.management.orders.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="numCustomer", length = 20, nullable=false)
	private String numCustomer;
	
	@OneToMany(mappedBy = "customer")
    private Collection<Order> order;

	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Integer id, String numCustomer) {
		super();
		this.id = id;
		this.numCustomer = numCustomer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumCustomer() {
		return numCustomer;
	}

	public void setNumCustomer(String numCustomer) {
		this.numCustomer = numCustomer;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", numCustomer=" + numCustomer + ", order=" + order + "]";
	}
	
	
	
}
