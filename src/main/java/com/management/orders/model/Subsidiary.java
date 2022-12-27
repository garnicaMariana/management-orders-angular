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
@Table(name="subsidiary")
public class Subsidiary {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length = 20, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "subsidiary")
    private Collection<Order> order;
	
	public Subsidiary() {
		// TODO Auto-generated constructor stub
	}
	
	public Subsidiary(Integer id, String name, Collection<Order> order) {
		super();
		this.id = id;
		this.name = name;
		this.order = order;
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
		return "Subsidiary [id=" + id + ", name=" + name + ", order=" + order + "]";
	}
	
}
