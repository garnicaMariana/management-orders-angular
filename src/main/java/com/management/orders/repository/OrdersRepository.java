package com.management.orders.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.orders.model.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer>{
	
	@Query(value = "SELECT * from orders where order_date BETWEEN :startDate AND :endDate limit 7", nativeQuery=true)
	List<Order> getAllBetweenDates(Date startDate,Date endDate);

}
