package com.cg.inventory.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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
import com.cg.inventory.model.Customer;
import com.cg.inventory.model.Order;
import com.cg.inventory.model.Product;
import com.cg.inventory.services.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The OrderControllerTest class has test methods for the order controller layer
 * 
 * @author sharique nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	private List<Product> products;
	private Customer customer;
	private Category category;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderServiceImpl orderService;

	@Before
	public void setUp() throws Exception {
		category = new Category("School", "This category contains school stuff");
		category.setCategoryId(1L);
		Product product = new Product("bag", "bag for school", 30L, 200f, category);
		product.setProductId(10L);
		products = new ArrayList<>();
		products.add(product);
		customer = new Customer("shariq", "9901738798", "bangalore", "shariq@gmail.com", "shariq store");
		customer.setCustomerId(10L);
	}

	@After
	public void tearDown() throws Exception {
		products.clear();
		customer = null;
		category = null;
	}

	@Test
	public void testViewAllOrders() throws Exception {
		String URI = "/api/v1/order";
		Order order = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 7, 30, 16, 9), 1200f, customer, products);
		Order order1 = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 8, 30, 14, 15), 1400f, customer, products);
		Order order2 = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 9, 30, 13, 10), 1600f, customer, products);

		List<Order> orders = new ArrayList<>();
		orders.add(order2);
		orders.add(order1);
		orders.add(order);

		Mockito.when(orderService.viewAllOrders()).thenReturn(orders);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(orders)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testCreateOrder() throws Exception {
		String URI = "/api/v1/order";
		Order order = new Order(LocalDateTime.of(2022, 6, 30, 16, 9), LocalDateTime.of(2022, 7, 30, 16, 9), 1200f, customer, products);

		Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn(order);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(order)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(order)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testDeleteOrder() throws Exception {
		String URI = "/api/v1/order/{orderId}";
		Order order = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 7, 30, 16, 9), 1200f, customer, products);
		order.setOrderId(10L);

		doNothing().when(orderService).deleteOrder(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 10L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewOrderById() throws Exception {
		String URI = "/api/v1/order/{orderId}";
		Order order = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 7, 30, 16, 9), 1200f, customer, products);
		order.setOrderId(20L);

		Mockito.when(orderService.viewOrderById(Mockito.anyLong())).thenReturn(order);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 20L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(order)).isEqualTo(jsonOutput);
	}

	@Test
	public void testUpdateOrder() throws Exception {
		String URI = "/api/v1/order/{orderId}";
		Order order = new Order(LocalDateTime.of(2022, 6, 30, 16, 9), LocalDateTime.of(2022, 7, 30, 16, 9), 1200f, customer, products);
		order.setOrderId(30L);

		Mockito.when(orderService.updateOrder(Mockito.anyLong(), Mockito.any(Order.class))).thenReturn(order);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.put(URI, 30L).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(order)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(order)).isEqualTo(jsonOutput);
	}

}
