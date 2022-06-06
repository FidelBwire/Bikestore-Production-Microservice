package com.app.bikestore.production.brand.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.bikestore.exception.handlers.ResourceNotFoundException;
import com.app.bikestore.production.brand.entity.Brand;
import com.app.bikestore.production.brand.repository.BrandRepository;
import com.app.bikestore.production.brand.service.BrandService;
import com.app.bikestore.production.product.DTO.ProductDTO;
import com.app.bikestore.production.product.entity.Product;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand getBrand(Long brandId) {
		return brandRepository.findById(brandId)
					.orElseThrow(() -> new ResourceNotFoundException
							("No brand with id " + brandId));
	}

	@Override
	public Brand updateBrand(Brand brand) {
		Long brandId = brand.getId();
		if(!(brandRepository.existsById(brandId)))
			throw new ResourceNotFoundException("No brand with id " + brandId);
		else
			return brandRepository.save(brand);
	}

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(Long brandId) {
		brandRepository.deleteById(brandId);
	}
	
	@Override
	public List<Brand> getAllBrands() {
		return (List<Brand>) brandRepository.findAll();
	}

	@Override
	public Page<Brand> getBrands(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Brand> brandsPage = brandRepository.findAll(pageRequest);
				
		List<Brand> brandsList = new ArrayList<>();
		brandsPage.forEach(brand -> brandsList.add(brand));
		
		Page<Brand> results = new PageImpl<>(
				brandsList, pageRequest, brandRepository.count());
		
		return results;
	}
}
