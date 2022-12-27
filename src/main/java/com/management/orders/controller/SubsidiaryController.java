package com.management.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.orders.model.Subsidiary;
import com.management.orders.repository.SubsidiaryRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class SubsidiaryController {

	@Autowired
	private SubsidiaryRepository subsidiaryRepository;
	
	@GetMapping("getSubsidiary/{name}")
	public ResponseEntity<Subsidiary> getSubsidiaryByName(@PathVariable String name){
		Subsidiary subsidiary = subsidiaryRepository.findByName(name);
		return ResponseEntity.ok(subsidiary);
	}

}
