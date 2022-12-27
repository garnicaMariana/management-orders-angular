package com.management.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.orders.model.Subsidiary;

public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Integer> {
	
	public Subsidiary findByName(String name);

}
