package com.cg.inventory.services;

import java.util.List;

import com.cg.inventory.model.Product;

public interface ProductService {

	Product addProduct(Product product);

	List<Product> viewAllProducts();

	Product updateProduct(Long productId, Product product);

	void removeProduct(Long productId);

	Product viewProductById(Long productId);

}