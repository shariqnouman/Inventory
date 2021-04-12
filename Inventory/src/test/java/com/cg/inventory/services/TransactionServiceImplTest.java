package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.model.Category;
import com.cg.inventory.model.Customer;
import com.cg.inventory.model.Order;
import com.cg.inventory.model.Product;
import com.cg.inventory.model.Transaction;
import com.cg.inventory.repositories.TransactionRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TransactionServiceImpl.class)
public class TransactionServiceImplTest {

	private Order order;
	private List<Product> products;
	private Customer customer;
	private Category category;

	@Autowired
	private TransactionServiceImpl transactionService;

	@MockBean
	private TransactionRepository transactionRepository;

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
	public void testViewAllTransactions() {
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

		Mockito.when(transactionRepository.findAll()).thenReturn(transactions);
		assertThat(transactionService.viewAllTransactions()).isEqualTo(transactions);
		assertEquals(3, transactionService.viewAllTransactions().size());

		verify(transactionRepository, times(2)).findAll();
	}

	@Test
	public void testCreateTransaction() {
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);

		Mockito.when(transactionRepository.saveAndFlush(transaction)).thenReturn(transaction);
		assertThat(transactionService.createTransaction(transaction)).isEqualTo(transaction);

		verify(transactionRepository).saveAndFlush(transaction);
	}

	@Test
	public void testDeleteTransaction() {
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(10L);

		transactionService.deleteTransaction(transaction.getTransactionId());
		verify(transactionRepository, times(1)).deleteById(transaction.getTransactionId());
	}

	@Test
	public void testViewTransactionById() {
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(20L);

		Mockito.when(transactionRepository.findById(transaction.getTransactionId()))
				.thenReturn(Optional.of(transaction));
		assertThat(transactionService.viewTransactionById(20L)).isEqualTo(transaction);

		verify(transactionRepository).findById(transaction.getTransactionId());
	}

	@Test
	public void testUpdateTransaction() {
		Transaction transaction = new Transaction(LocalDateTime.of(2022, 6, 30, 16, 9), 1500f, 100f, "cash", 1600f, 0f,
				order);
		transaction.setTransactionId(30L);

		Transaction updatedTransaction = transaction;
		updatedTransaction.setTransactionId(30L);
		updatedTransaction.setDueAmount(0f);
		updatedTransaction.setGst(200f);
		updatedTransaction.setTransactionDate(LocalDateTime.of(2021, 6, 30, 20, 20));
		updatedTransaction.setOrder(order);
		updatedTransaction.setPaymentType("cash");
		updatedTransaction.setPaidAmount(1300f);
		updatedTransaction.setTotalPrice(1500f);

		Mockito.when(transactionRepository.saveAndFlush(updatedTransaction)).thenReturn(updatedTransaction);
		Mockito.when(transactionRepository.findById(transaction.getTransactionId()))
				.thenReturn(Optional.of(transaction));
		transactionService.updateTransaction(transaction.getTransactionId(), updatedTransaction);

		assertThat(transaction.getDueAmount()).isEqualTo(updatedTransaction.getDueAmount());
		verify(transactionRepository).saveAndFlush(updatedTransaction);
	}

}
