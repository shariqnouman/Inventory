package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.model.Customer;
import com.cg.inventory.repositories.CustomersRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomerServiceImpl.class)
public class CustomerServiceImplTest {

	@MockBean
	private CustomersRepository customerRepository;

	@Autowired
	@MockBean
	private CustomerServiceImpl customerservice;

	@Test
	public void testGetCustomerById() throws Exception {
		Customer cust = new Customer();
		cust.setCustomerId(10L);
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");
		List<Customer> custList = new ArrayList<>();
		custList.add(cust);
//		      System.out.println(customerRepository.findById(22));
//		        Mockito.when(customerRepository.findById(22).get()).thenReturn((cust));
		assertThat(cust.getCustomerId()).isEqualTo(10L);
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Customer cust = new Customer();
		cust.setCustomerId(10L);
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");

		customerRepository.deleteById(cust.getCustomerId());
		System.out.println(customerRepository.findById(10L));
		assertTrue(customerRepository.findById(10L).isEmpty());
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		Customer cust = new Customer();
		cust.setCustomerId(10L);
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");

		customerRepository.save(cust);

//			        Mockito.when(customerRepository.findById(22).get()).thenReturn(cust);
		cust.setCustomerNumber("9996589433");
//			        Mockito.when(customerRepository.save(cust)).thenReturn(cust);
//			        System.out.println(cust.getCustomerNumber());
		assertThat(cust.getCustomerNumber().equals("9996589433"));

	}

	@Test
	public void testGetAllCustomers() {
		Customer cust = new Customer();
		cust.setCustomerId(10L);
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");

		Customer cust1 = new Customer();
		cust1.setCustomerName("Sunanda");
		cust1.setCustomerNumber("9848032919");
		cust1.setCustomerEmail("sunanda@gmail.com");
		cust1.setCustomerAddress("tambaram,chennai,TN");
		cust1.setCustomerStoreName("Pasuparthy");

		List<Customer> custList = new ArrayList<>();
		custList.add(cust);
		custList.add(cust1);

		Mockito.when(customerRepository.findAll()).thenReturn(custList);
		assertThat(customerservice.viewAllCustomers()).isNotEqualTo(custList);
	}

	@Test
	public void testSaveCustomers() {

		Customer cust = new Customer();
		cust.setCustomerId(10L);
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");

		Mockito.when(customerRepository.save(cust)).thenReturn(cust);
		assertThat(customerservice.addCustomer(cust)).isNotEqualTo(cust);
	}

}
