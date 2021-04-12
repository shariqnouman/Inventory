package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.model.Category;
import com.cg.inventory.repositories.CategoryRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CategoryServiceImpl.class)
public class CategoryServiceImplTest {

	@MockBean
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Test
	public void testViewAllCategories() {
		Category category = new Category("School", "This category contains school stuff");
		Category category1 = new Category("Laptop", "This category contains Laptop stuff");
		Category category2 = new Category("Kitchen", "This category contains Kitchen stuff");

		List<Category> categories = new ArrayList<>();
		categories.add(category2);
		categories.add(category1);
		categories.add(category);

		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		assertThat(categoryService.viewAllCategories()).isEqualTo(categories);
		assertEquals(3, categoryService.viewAllCategories().size());

		verify(categoryRepository, times(2)).findAll();
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category("School", "This category contains school stuff");

		Mockito.when(categoryRepository.saveAndFlush(category)).thenReturn(category);
		assertThat(categoryService.createCategory(category)).isEqualTo(category);

		verify(categoryRepository).saveAndFlush(category);
	}

	@Test
	public void testDeleteCategory() {
		Category category = new Category("School", "This category contains school stuff");
		category.setCategoryId(10L);

		categoryService.deleteCategory(category.getCategoryId());
		verify(categoryRepository, times(1)).deleteById(category.getCategoryId());
	}

	@Test
	public void testViewCategoryById() {
		Category category = new Category("School", "This category contains school stuff");
		category.setCategoryId(10L);

		Mockito.when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
		assertThat(categoryService.viewCategoryById(10L)).isEqualTo(category);

		verify(categoryRepository).findById(category.getCategoryId());
	}

	@Test
	public void testUpdateCategory() {
		Category category = new Category("School", "This category contains school stuff");
		category.setCategoryId(10L);

		Category updatedCategory = category;
		updatedCategory.setCategoryId(10L);
		updatedCategory.setCategoryName("laptop");
		updatedCategory.setCategoryDescription("This category contains laptop stuff");

		Mockito.when(categoryRepository.saveAndFlush(updatedCategory)).thenReturn(updatedCategory);
		Mockito.when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
		categoryService.updateCategory(category.getCategoryId(), updatedCategory);

		assertThat(category.getCategoryName()).isEqualTo(updatedCategory.getCategoryName());
		verify(categoryRepository).saveAndFlush(updatedCategory);
	}

}
