package com.app.bikestore.production.product.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.bikestore.production.product.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
