package com.flyhub.ideamanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.flyhub.ideamanagementsystem.entity.Priority;

import com.flyhub.ideamanagementsystem.service.PriorityServices;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/")
public class PriorityController {
	
@Autowired private PriorityServices priorityService;
	
	@GetMapping("priority")
	public List<Priority> viewPriorityList() {
		
		return priorityService.listAll();
		}

}
