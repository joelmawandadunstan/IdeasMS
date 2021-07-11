package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entity.CategoryEntity;
import com.flyhub.ideamanagementsystem.repositories.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	public List<CategoryEntity> listAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Optional<CategoryEntity> getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}
	
	public CategoryEntity saveCategory(CategoryEntity category) {
		return categoryRepository.save(category);
	}
	
	public void deleteByCategoryId(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
	public void deleteAllCategories() {
		categoryRepository.deleteAll();
	}

}
