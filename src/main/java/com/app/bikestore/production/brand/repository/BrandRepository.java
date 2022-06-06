package com.app.bikestore.production.brand.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.bikestore.production.brand.entity.Brand;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
}
