package com.cg.inventory.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.inventory.model.Category;
import com.cg.inventory.services.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The CategoryControllerTest class has test methods for the category controller
 * layer
 * 
 * @author sharique nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CategoryServiceImpl categoryService;

	@Test
	public void testViewAllCategories() throws Exception {
		String URI = "/api/v1/category";
		Category category = new Category("School", "This category contains school stuff");
		Category category1 = new Category("Laptop", "This category contains Laptop stuff");
		Category category2 = new Category("Kitchen", "This category contains Kitchen stuff");

		List<Category> categories = new ArrayList<>();
		categories.add(category2);
		categories.add(category1);
		categories.add(category);

		Mockito.when(categoryService.viewAllCategories()).thenReturn(categories);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(categories)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testCreateCategory() throws Exception {
		String URI = "/api/v1/category";
		Category category = new Category("Laptop", "This category contains Laptop stuff");

		Mockito.when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(category);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(category)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(category)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testDeleteCategory() throws Exception {
		String URI = "/api/v1/category/{categoryId}";
		Category category = new Category("Laptop", "This category contains Laptop stuff");
		category.setCategoryId(10L);

		doNothing().when(categoryService).deleteCategory(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 10L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewCategoryById() throws Exception {
		String URI = "/api/v1/category/{categoryId}";
		Category category = new Category("Laptop", "This category contains Laptop stuff");
		category.setCategoryId(20L);

		Mockito.when(categoryService.viewCategoryById(Mockito.anyLong())).thenReturn(category);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 20L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(category)).isEqualTo(jsonOutput);
	}

	@Test
	public void testUpdateCategory() throws Exception {
		String URI = "/api/v1/category/{categoryId}";
		Category category = new Category("Laptop", "This category contains Laptop stuff");
		category.setCategoryId(30L);

		Mockito.when(categoryService.updateCategory(Mockito.anyLong(), Mockito.any(Category.class)))
				.thenReturn(category);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.put(URI, 30L).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(category)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(category)).isEqualTo(jsonOutput);
	}

}
