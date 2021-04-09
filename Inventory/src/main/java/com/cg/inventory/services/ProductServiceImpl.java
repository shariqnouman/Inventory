package com.cg.inventory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Product;
import com.cg.inventory.repositories.ProductsRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsRepository productsRepository;

	@Override
	public Product addProduct(Product product) {
		return productsRepository.saveAndFlush(product);
	}

	@Override
	public List<Product> viewAllProducts() {
		return productsRepository.findAll();
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		Product existingProduct = productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this Id : " + productId));
		BeanUtils.copyProperties(product, existingProduct, "productId");
		return productsRepository.saveAndFlush(existingProduct);
	}

	@Override
	public void removeProduct(Long productId) {
		productsRepository.deleteById(productId);
	}

	@Override
	public Product viewProductById(Long productId) {
		return productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found for this Id : " + productId));
	}
}
