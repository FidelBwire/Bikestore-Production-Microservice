package com.app.bikestore.production.common.images.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bikestore.production.common.images.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);
}
