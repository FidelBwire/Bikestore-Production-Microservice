package com.app.bikestore.production.feign_service_clients.production_service_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.bikestore.production.brand.entity.Brand;
import com.app.bikestore.production.category.entity.Category;

@FeignClient(name = "PRODUCTION-SERVICE")
public interface ProductionServiceClient {

	@GetMapping("/production/brands/{brandId}")
	public Brand getBrand(@PathVariable Long brandId);
	
	@GetMapping("/production/categories/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId);
}
