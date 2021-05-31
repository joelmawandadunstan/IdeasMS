package com.flyhub.ideamanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entity.Category;
import com.flyhub.ideamanagementsystem.entity.Postfix;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;

@Service
public class CategoryServices {
	
	@Autowired CategoryRepository categoryRepo;
	
	 public List<Category> listAll(){ 
		  return categoryRepo.findAll(); 
		  }

}
