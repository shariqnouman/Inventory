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
import com.cg.inventory.model.Product;
import com.cg.inventory.services.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The ProductControllerTest class has test methods for the product controller
 * layer
 * 
 * @author sharique nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductServiceImpl productService;

	@Test
	public void testAddProduct() throws Exception {

		String URI = "/api/v1/product/";
		Category cc = new Category("Electronics", "This is a site for electronics ");
		cc.setCategoryId(1L);
		Product product = new Product();
		product.setProductId(10L);
		product.setProductName("Redmi Mobile");
		product.setProductPrice(100F);
		product.setProductDescription("This is a new and best selling mobile");
		product.setProductTag("Mobiles");
		product.setProductLocation("India");
		product.setProductQuantity(100L);
		product.setProductUnit("Ram");
		product.setProductSize("6gb,8gb");

		String jsonInput = this.converttoJson(product);
		System.out.println(jsonInput);

		Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(product);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonOutput).isEqualTo(this.converttoJson(product));
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewAllProducts() throws Exception {

		String URI = "/api/v1/product/";
		Product product = new Product();
		product.setProductId(10L);
		product.setProductName("Redmi Mobile");
		product.setProductPrice(100F);
		product.setProductDescription("This is a new and best selling mobile");
		product.setProductTag("Mobiles");
		product.setProductLocation("India");

		Product prod = new Product();
		prod.setProductName("Oneplus Mobile");
		prod.setProductLocation("Andhra");
		prod.setProductPrice(10100F);
		prod.setProductTag("Mobiles");
		prod.setProductDescription("This is the new model of oneplus trending right now!");
		List<Product> checklist = new ArrayList<>();
		checklist.add(product);
		checklist.add(prod);
		String jsonInput = this.converttoJson(checklist);
		Mockito.when(productService.viewAllProducts()).thenReturn(checklist);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testViewProductById() throws Exception {
		String URI = "/api/v1/product/{id}";
		Product product = new Product();
		product.setProductId(10L);
		product.setProductName("Redmi Mobile");
		product.setProductPrice(100F);
		product.setProductDescription("This is a new and best selling mobile");
		product.setProductTag("Mobiles");
		product.setProductLocation("India");

		String jsonInput = this.converttoJson(product);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 10).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isNotEqualTo(jsonOutput);

	}

	@Test
	public void testUpdateProduct() throws Exception {
		String URI = "/api/v1/product/{id}";
		Category cc = new Category("Electronics", "This is a site for electronics ");
		cc.setCategoryId(1L);
		Product product = new Product();
		product.setProductId(10L);
		product.setProductName("Redmi Mobile");
		product.setProductPrice(100F);
		product.setProductDescription("This is a new and best selling mobile");
		product.setProductTag("Mobiles");
		product.setProductLocation("India");
		product.setProductQuantity(100L);
		product.setProductUnit("Ram");
		product.setProductSize("6gb,8gb");
		String jsonInput = this.converttoJson(product);
		Mockito.when(productService.updateProduct(Mockito.any(), Mockito.any())).thenReturn(product);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 10)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	@Test
	public void testRemoveProduct() throws Exception {
		String URI = "/api/v1/product/{id}";
		Product prod = new Product();
		prod.setProductId(11L);
		prod.setProductName("Realme");
		prod.setProductPrice(100F);
		prod.setProductDescription("This is a brand new mobile");
		prod.setProductLocation("Bharath");
		prod.setProductTag("Mobiles");

		doNothing().when(productService).removeProduct(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 11L).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	private String converttoJson(Object manager) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(manager);
	}

}
