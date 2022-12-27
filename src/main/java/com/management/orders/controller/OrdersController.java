package com.management.orders.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.orders.exceptions.ResourceNotFoundException;
import com.management.orders.model.Channel;
import com.management.orders.model.Order;
import com.management.orders.model.Subsidiary;
import com.management.orders.repository.ChannelRepository;
import com.management.orders.repository.OrdersRepository;
import com.management.orders.repository.SubsidiaryRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class OrdersController {

	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private SubsidiaryRepository subsidiaryRepository;


	
	@GetMapping("/orders")
	public List<Order> listAllOrders(){
		return orderRepository.findAll();
	}
	
	@GetMapping("/orders/time/{startDate}/{endDate}")
	public List<Order> getAllBetweenDates(@PathVariable String startDate, @PathVariable String endDate) {
	    Date date2 = null, date1 = null;
	    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 date1 = sdf.parse(startDate);
			 date2 = sdf.parse(endDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderRepository.getAllBetweenDates(date1, date2);
	}
	
	@PostMapping("/orders")
	public Order saveOrder(@RequestBody Order order) {
		return orderRepository.save(order);
	}
	
	@PostMapping("/orders/load")
	public ResponseEntity<HttpStatus> saveOrders(@RequestBody List<Order> orders) {
		System.out.println(orders);
		orderRepository.saveAll(orders);
	    
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	 
	@GetMapping("orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer id){
		
		Order order = orderRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Element not found"));
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order detailsOrder){
		
		System.out.println(detailsOrder);
		Order order = orderRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Element not found"));
		
		Channel channel = channelRepository.findById(detailsOrder.getChannel().getId())
				.orElseThrow(()->new ResourceNotFoundException("Element not found"));
		
		Subsidiary subsidiary = subsidiaryRepository.findById(detailsOrder.getSubsidiary().getId())
				.orElseThrow(()->new ResourceNotFoundException("Element not found"));
		
		System.out.println(subsidiary.getId());
						
		order.setChannel(channel);
		order.setSubsidiary(subsidiary);
		order.setTransaction("TF");
	
		Order updatedOrder = orderRepository.save(order);

		return ResponseEntity.ok(updatedOrder);
		
	}
	
	
	@DeleteMapping("/orders/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteOrder(@PathVariable Integer id){
		
		Order order = orderRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Element not found"));
		
		orderRepository.delete(order);
		Map<String, Boolean> answer = new HashMap<>();
		answer.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(answer);
    }
	
	
	
}
