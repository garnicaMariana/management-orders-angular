package com.management.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.orders.exceptions.ResourceNotFoundException;
import com.management.orders.model.Channel;
import com.management.orders.repository.ChannelRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class ChannelController {

	@Autowired
	private ChannelRepository channelRepository;
	
	@GetMapping("getChannel/{name}")
	public ResponseEntity<Channel> getChannelByName(@PathVariable String name){
		Channel channel = channelRepository.findByName(name);
		return ResponseEntity.ok(channel);
	}


}
