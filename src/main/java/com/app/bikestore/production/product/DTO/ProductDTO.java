package com.app.bikestore.production.product.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.app.bikestore.production.product.entity.Product;

public class ProductDTO {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Positive
	private Long brandId;
	@Positive
	private Long categoryId;
	
//	@PastOrPresent
	private int modelYear;
	
	@Positive
	private BigDecimal listPrice;
	
//	@Size(max = 251)
	private String description;
	
	public ProductDTO() { }

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.brandId = product.getBrand().getId();
		this.categoryId = product.getCategory().getId();
		this.modelYear = product.getModelYear();
		this.listPrice = product.getListPrice();
		this.description = product.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", brandId=" + brandId + ", categoryId=" + categoryId
				+ ", modelYear=" + modelYear + ", listPrice=" + listPrice + ", description=" + description + "]";
	}	
	
}
