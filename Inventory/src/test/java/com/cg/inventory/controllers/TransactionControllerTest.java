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
import com.cg.inventory.model.Transaction;
import com.cg.inventory.services.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The TransactionControllerTest class has test methods for the transaction
 * controller layer
 * 
 * @author sharique nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	private Order order;
	private List<Product> products;
	private Customer customer;
	private Category category;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TransactionServiceImpl transactionService;

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
		order = new Order(LocalDateTime.now(), LocalDateTime.of(2021, 7, 30, 16, 9), 1200f, customer, products);
	}

	@After
	public void tearDown() throws Exception {
		products.clear();
		customer = null;
		category = null;
		order = null;
	}

	@Test
	public void testViewAllTransactions() throws Exception {
		String URI = "/api/v1/transaction";
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		Transaction transaction1 = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		Transaction transaction2 = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction2);
		transactions.add(transaction1);
		transactions.add(transaction);

		Mockito.when(transactionService.viewAllTransactions()).thenReturn(transactions);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(transactions)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testCreateTransaction() throws Exception {
		String URI = "/api/v1/transaction";
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);

		Mockito.when(transactionService.createTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(transaction)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(transaction)).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testDeleteTransaction() throws Exception {
		String URI = "/api/v1/transaction/{transactionId}";
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(10L);

		doNothing().when(transactionService).deleteTransaction(Mockito.anyLong());
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 10L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewTransactionById() throws Exception {
		String URI = "/api/v1/transaction/{transactionId}";
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(20L);

		Mockito.when(transactionService.viewTransactionById(Mockito.anyLong())).thenReturn(transaction);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 20L).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(transaction)).isEqualTo(jsonOutput);
	}

	@Test
	public void testUpdateTransaction() throws Exception {
		String URI = "/api/v1/transaction/{transactionId}";
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(30L);

		Mockito.when(transactionService.updateTransaction(Mockito.anyLong(), Mockito.any(Transaction.class)))
				.thenReturn(transaction);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.put(URI, 30L).accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(transaction)).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(objectMapper.writeValueAsString(transaction)).isEqualTo(jsonOutput);
	}

}
