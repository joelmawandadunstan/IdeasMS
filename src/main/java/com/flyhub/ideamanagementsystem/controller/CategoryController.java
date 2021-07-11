package com.flyhub.ideamanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.entity.CategoryEntity;
import com.flyhub.ideamanagementsystem.service.CategoryService;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<List<CategoryEntity>> listAllCategories(){
		try {
			List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
			categories.addAll(categoryService.listAllCategories());
			if(categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Optional<CategoryEntity>> getCategoryById(@PathVariable("categoryId") Long CategoryId){
		try {
			Optional<CategoryEntity> category = categoryService.getCategoryById(CategoryId);
			return new ResponseEntity<>(category, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity categoryEntity){
		try {
			CategoryEntity category = categoryService.saveCategory(categoryEntity);
			return new ResponseEntity<>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryEntity> partialUpdateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryEntity categoryEntity) {
        Optional<Optional<CategoryEntity>> category = Optional.ofNullable(categoryService.getCategoryById(categoryId));

        if (category.isPresent()) {
            categoryEntity.setCategoryId(categoryId);
            return new ResponseEntity<>(categoryService.saveCategory(categoryEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/{categoryId}")
    public ResponseEntity<CategoryEntity> fullUpdateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryEntity categoryEntity) {
        Optional<Optional<CategoryEntity>> categoryOptional = Optional.ofNullable(categoryService.getCategoryById(categoryId));

        if (categoryOptional.isPresent()) {
        	categoryEntity.setCategoryId(categoryId);
            return new ResponseEntity<>(categoryService.saveCategory(categoryEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<HttpStatus> deleteByCategoryId(@PathVariable("categoryId") Long categoryId){
		try {
			categoryService.deleteByCategoryId(categoryId);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("")
	public ResponseEntity<HttpStatus> deleteAllCategories(){
		try {
			categoryService.deleteAllCategories();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
