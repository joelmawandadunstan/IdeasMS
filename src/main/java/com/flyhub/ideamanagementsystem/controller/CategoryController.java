package com.flyhub.ideamanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entity.Category;

import com.flyhub.ideamanagementsystem.service.CategoryServices;


//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
	
	@Autowired private CategoryServices categoryService;
	
	@GetMapping
	public List<Category> viewCategoryList() {
		
		return categoryService.listAll();
		}

}
