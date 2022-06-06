package com.app.bikestore.production.category.service;

import java.util.List;

import javax.validation.Valid;

import com.app.bikestore.production.category.entity.Category;

public interface CategoryService {
	List<Category> findAllCategories();
	Object findCategoryByName(String name);
	Category saveCategory(@Valid Category category);
	Category findCategoryById(Long id);
	Category updateCategory(Long id, @Valid Category category);
	void deleteCategory(Long id);
}
