package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.model.Product;
import com.cg.inventory.repositories.ProductsRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductServiceImpl.class)
public class ProductServiceImplTest {

	@MockBean
	private ProductsRepository prodrepo;

	@Autowired
	private ProductServiceImpl service;
	@Mock
	private Product product1;
	@Mock
	private Product product2;

	@BeforeEach
	public void setUp() throws Exception {
		product1 = new Product();
		product1.setProductId(100L);
		product1.setProductName("Blazer");
		product1.setProductPrice(100F);
		product1.setProductLocation("Kerala");
		product1.setProductDescription("This is a brand new blazer");
		product1.setProductTag("Clothing");
		product2 = new Product();
		product2.setProductName("Tshirt");
		product2.setProductId(11L);
		product2.setProductPrice(100F);
		product2.setProductLocation("Andhra");
		product2.setProductDescription("This is a brand new tshirt");
		product2.setProductTag("Clothing");
	}

	@AfterEach
	public void tearDown() throws Exception {
		product1 = null;
		product2 = null;
	}

	@Test
	public void testAddProduct() throws Exception {
		product1 = new Product();
		prodrepo.save(product1);
		Mockito.when(prodrepo.save(product1)).thenReturn(product1);
		assertThat(service.addProduct(product1)).isNotEqualTo(product1);
	}

	@Test
	public void testViewAllProducts() throws Exception {
		List<Product> prodlist = new ArrayList<>();
		prodlist.add(product1);
		prodlist.add(product2);
		Mockito.when(prodrepo.findAll()).thenReturn(prodlist);
		assertThat(service.viewAllProducts()).isEqualTo(prodlist);

	}

	@Test
	public void testUpdateProduct() throws Exception {
		Mockito.when(prodrepo.save(product1)).thenReturn(product1);
		assertTrue(prodrepo.findById((long) 1).isEmpty());

	}

	@Test
	public void testRemoveProduct() throws Exception {
		product1 = new Product();
		product1.setProductId(10L);
		product1.setProductName("Blazer");
		product1.setProductPrice(100F);
		product1.setProductLocation("Kerala");
		product1.setProductDescription("This is a brand new blazer");
		product1.setProductTag("Clothing");
		prodrepo.save(product1);

		prodrepo.deleteById(product1.getProductId());
		System.out.println(prodrepo.findById((long) 10));
		assertTrue(prodrepo.findById((long) 10).isEmpty());

	}

	@Test
	public void testViewProductById() throws Exception {
		product1 = new Product();
		prodrepo.save(product1);
		Mockito.when(prodrepo.save(product1)).thenReturn(product1);
		assertTrue(prodrepo.findById((long) 10).isEmpty());

	}

}
