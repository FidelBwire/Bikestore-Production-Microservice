package com.app.bikestore.production.brand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bikestore.production.brand.entity.Brand;
import com.app.bikestore.production.brand.service.BrandService;

@RestController
@RequestMapping(value = "/production/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Brand>> getAllBrands() {
		System.out.println("getAllBrands called");
		return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<Page<Brand>> getBrands(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		System.out.println("getBrands called");
		Page<Brand> brands = brandService.getBrands(page,size);
		return new ResponseEntity<Page<Brand>>(brands, HttpStatus.OK);
	}
	
	@GetMapping("/{brandId}")
	public ResponseEntity<Brand> getBrand(@PathVariable("brandId") Long brandId) {
		return new ResponseEntity<>(brandService.getBrand(brandId), HttpStatus.OK);
	}
	
	@PutMapping("/{brandId}")
	public ResponseEntity<Brand> updateBrand(@PathVariable("brandId") Long brandId, 
			@RequestBody @Valid Brand brand) {
		brand.setId(brandId);
		return new ResponseEntity<>(brandService.updateBrand(brand), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		return new ResponseEntity<>(brandService.createBrand(brand), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{brandId}")
	public void deleteBrand(@PathVariable("brandId") Long brandId) {
		brandService.deleteBrand(brandId);
	}
}
