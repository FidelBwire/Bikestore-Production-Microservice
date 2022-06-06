package com.app.bikestore.production.brand.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.app.bikestore.production.brand.entity.Brand;

public interface BrandService {
	List<Brand> getAllBrands();
	Brand getBrand(Long brandId);
	Brand updateBrand(@Valid Brand brand);
	Brand createBrand(Brand brand);
	void deleteBrand(Long brandId);
	Page<Brand> getBrands(int page, int size);
}
