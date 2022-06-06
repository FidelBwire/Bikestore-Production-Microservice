package com.app.bikestore.production.product.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.app.bikestore.production.product.DTO.ProductDTO;

public interface ProductService {
	List<ProductDTO> getAllProducts();
	Page<ProductDTO> getAllProducts(int page, int size);
	ProductDTO updateProduct(@Valid ProductDTO productDTO);
	ProductDTO getProduct(Long productId);
	ProductDTO createProduct(@Valid ProductDTO productDTO);
	void deleteProduct(Long productId);	
}
