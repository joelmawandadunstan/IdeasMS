package com.flyhub.ideamanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entity.Priority;
import com.flyhub.ideamanagementsystem.repositories.PriorityRepository;

@Service
public class PriorityServices {
	
	@Autowired PriorityRepository priorityRepo;
	
	 public List<Priority> listAll(){ 
		  return priorityRepo.findAll(); 
		  }

}
