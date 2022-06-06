package com.app.bikestore.production.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.bikestore.exception.handlers.ResourceNotFoundException;
import com.app.bikestore.production.feign_service_clients.production_service_client.ProductionServiceClient;
import com.app.bikestore.production.product.DTO.ProductDTO;
import com.app.bikestore.production.product.entity.Product;
import com.app.bikestore.production.product.repository.ProductRepository;
import com.app.bikestore.production.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductionServiceClient productionServiceClient;
	
	private List<ProductDTO> convertToProductDTOList(List<Product> products) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		products.forEach(product -> productDTOs.add(new ProductDTO(product)));
		return productDTOs;
	}
	
	private ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO(product);
		return productDTO;
	}
	
	private Product convertToProduct(ProductDTO dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setListPrice(dto.getListPrice());
		product.setBrand(productionServiceClient.getBrand(dto.getBrandId()));
		product.setCategory(productionServiceClient.getCategory(dto.getCategoryId()));
		return product;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> allProducts = new ArrayList<>();
		productRepository.findAll().forEach(product -> allProducts.add(product));
		List<ProductDTO> productDTOs = convertToProductDTOList(allProducts);
		return productDTOs;
	}
	
	@Override
	public Page<ProductDTO> getAllProducts(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Product> productsPage = productRepository.findAll(pageRequest);
				
		List<Product> products = new ArrayList<>();
		productsPage.forEach(product -> products.add(product));
		
		List<ProductDTO> productDTOList = convertToProductDTOList(products);
		Page<ProductDTO> pageDTOResult = new PageImpl<>(
				productDTOList, pageRequest, productRepository.count());
		
		return pageDTOResult;
	}

	@Override
	public ProductDTO updateProduct(@Valid ProductDTO productDTO) {
		Product product = convertToProduct(productDTO);
		product.setId(productDTO.getId());
		Product updatedProduct = productRepository.save(product);
		ProductDTO updatedProductDTO = convertToProductDTO(updatedProduct);
		return updatedProductDTO;
	}
	
	@Override
	public ProductDTO getProduct(Long productId) {
		Product product = productRepository.findById(productId)
							.orElseThrow(() -> new ResourceNotFoundException(
									"No product with id " + productId));
		ProductDTO productDTO = convertToProductDTO(product);
		return productDTO;
	}

	@Override
	public ProductDTO createProduct(@Valid ProductDTO productDTO) {
		Product createdProduct = productRepository.save(convertToProduct(productDTO));
		ProductDTO createdProductDTO = convertToProductDTO(createdProduct);
		return createdProductDTO;
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"No product with id " + productId));
		productRepository.deleteById(productId);
	}
}
