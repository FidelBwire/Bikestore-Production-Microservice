package com.app.bikestore.production.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bikestore.exception.handlers.ResourceNotFoundException;
import com.app.bikestore.production.category.entity.Category;
import com.app.bikestore.production.category.repository.CategoryRepository;
import com.app.bikestore.production.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("No category found with id="+id));
	}
	
	public Category findCategoryByName(String name) {
		return categoryRepository.findByName(name)
				.orElse(null);
	}

	public Category updateCategory(Long id, Category category) {
		categoryRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException
					("No category found with id="+id));
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("No category found with id="+id));
		categoryRepository.deleteById(id);
	}
}
