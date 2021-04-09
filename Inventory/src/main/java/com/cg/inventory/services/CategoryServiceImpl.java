package com.cg.inventory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Category;
import com.cg.inventory.repositories.CategoryRepository;

/**
 * The CategoryServiceImpl class provides access to JPA methods to create,
 * remove, view, and update categories for products
 * 
 * @author sharique nooman
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * This method returns list of all categories
	 * 
	 * @return list of categories
	 */
	@Override
	public List<Category> viewAllCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * This method takes category details and creates a new category for products
	 * 
	 * @param category
	 * @return category entity details
	 */
	@Override
	public Category createCategory(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	/**
	 * This method takes category Id and deletes the category
	 * 
	 * @param categoryId
	 */
	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	/**
	 * This method returns categories by searching with specific category Id
	 * 
	 * @param categoryId
	 * @return category details if found or else throw exception
	 */
	@Override
	public Category viewCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this Id : " + categoryId));
	}

	/**
	 * This method updates category details by searching with category Id
	 * 
	 * @param categoryId
	 * @param category
	 * @return category details if found or else throw exception
	 */
	@Override
	public Category updateCategory(Long categoryId, Category category) {
		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this Id : " + categoryId));
		BeanUtils.copyProperties(category, existingCategory, "categoryId");
		return categoryRepository.saveAndFlush(existingCategory);
	}
}
