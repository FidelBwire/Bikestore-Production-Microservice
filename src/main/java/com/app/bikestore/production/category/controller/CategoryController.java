package com.app.bikestore.production.category.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bikestore.production.category.entity.Category;
import com.app.bikestore.production.category.service.CategoryService;

@RestController
@RequestMapping(value = "/production/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> listCategories() {
		return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category, BindingResult result) {
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			throw new MethodArgumentNotValidException(null, result);
//		}		
//		if((categoryService.findCategoryByName(category.getName()) == null)) {
//			throw new ResourceAccessException("A category named " + category.getName() + "already exists!!");
//		}
		Category savedCategory = categoryService.saveCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) {
		return new ResponseEntity<>(categoryService.findCategoryById(categoryId), HttpStatus.OK);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, 
			@RequestBody @Valid Category category, BindingResult result ) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Category updatedCategory = categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
}
