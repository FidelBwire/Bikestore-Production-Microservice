
package com.app.bikestore.production.product.controller;

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

import com.app.bikestore.production.product.DTO.ProductDTO;
import com.app.bikestore.production.product.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/production/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAllProducts(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<ProductDTO> allProducts = productService.getAllProducts(page, size);
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> allProductsDTOs = productService.getAllProducts();
		return new ResponseEntity<>(allProductsDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") Long productId
			) {
		ProductDTO product = productService.getProduct(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") Long productId,
			@RequestBody @Valid ProductDTO productDTO) {
		productDTO.setId(productId);
		ProductDTO updatedProduct = productService.updateProduct(productDTO);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

//	@PutMapping("/{productId}")
//	public String updateProduct(@PathVariable("productId") Long productId,
//			@RequestBody @Valid ProductDTO productDTO) {
//		productDTO.setId(productId);
//		ProductDTO updatedProduct = productService.updateProduct(productDTO);		
//		return "updatedProduct: " + updatedProduct;
//	}

	
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
		ProductDTO savedProduct = productService.createProduct(productDTO);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
