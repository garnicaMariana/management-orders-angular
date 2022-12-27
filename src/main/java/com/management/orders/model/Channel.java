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
@Table(name="channel")
public class Channel {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length = 20, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "channel")
    private Collection<Order> order;
	
	public Channel() {
		// TODO Auto-generated constructor stub
	}	

	public Channel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", order=" + order + "]";
	}	
	
	
}
