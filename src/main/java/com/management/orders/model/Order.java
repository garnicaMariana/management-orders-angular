package com.management.orders.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_customer")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
	
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_channel")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Channel channel;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_subsidiary")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Subsidiary subsidiary;
	
	@Column(name="orderDate", nullable=false)
	private Date orderDate;
	
	@Column(name="transaction", length = 10, nullable=true)
	private String transaction;
	
	public Order() {
		this.orderDate = new Date();
	}
	
	public Order(Integer id, Customer customer, Channel channel, Subsidiary subsidiary,
			String transaction) {
		super();
		this.id = id;
		this.customer = customer;
		this.channel = channel;
		this.subsidiary = subsidiary;
		this.orderDate = new Date();
		this.transaction = transaction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Subsidiary getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(Subsidiary subsidiary) {
		this.subsidiary = subsidiary;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer.getNumCustomer() + ", channel=" +channel+ ", subsidiary=" + subsidiary
				+ ", orderDate=" + orderDate + ", transaction=" + transaction + "]";
	}
	
}
