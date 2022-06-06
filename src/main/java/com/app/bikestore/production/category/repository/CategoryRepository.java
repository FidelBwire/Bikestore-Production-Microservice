package com.app.bikestore.production.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bikestore.production.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Optional<Category> findByName(String name);
}
