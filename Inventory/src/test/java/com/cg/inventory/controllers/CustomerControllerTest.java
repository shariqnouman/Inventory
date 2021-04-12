package com.cg.inventory.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.cg.inventory.model.Customer;
import com.cg.inventory.services.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The CustomerControllerTest class has test methods for the order controller
 * layer
 * 
 * @author sharique nooman
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerServiceImpl customerservice;

	@Test
	public void testAddCustomer() throws Exception {
		String URI = "/api/v1/customer";
		Customer cust1 = new Customer();
		cust1.setCustomerName("Sarath Lekha");
		cust1.setCustomerNumber("8989545274");
		cust1.setCustomerEmail("sarathlekha@gmail.com");
		cust1.setCustomerAddress(" 19-12-215/c,Bairagipatteda,Tirupati,517501,AP");
		cust1.setCustomerStoreName("V mart");
		String jsonInput = this.converttoJson(cust1);

		Mockito.when(customerservice.addCustomer(Mockito.any(Customer.class))).thenReturn(cust1);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testViewCustomerById() throws Exception {
		String URI = "/api/v1/customer/{customerId}";
		Customer cust1 = new Customer();
		cust1.setCustomerName("Sarath Lekha");
		cust1.setCustomerNumber("8989545274");
		cust1.setCustomerEmail("sarathlekha@gmail.com");
		cust1.setCustomerAddress(" 19-12-215/c,Bairagipatteda,Tirupati,517501,AP");
		cust1.setCustomerStoreName("V mart");
		String jsonInput = this.converttoJson(cust1);
		Mockito.when(customerservice.viewCustomerById(Mockito.any())).thenReturn(cust1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testViewAllCustomers() throws Exception {
		String URI = "/api/v1/customer/{customerId}";

		Customer cust = new Customer();
		cust.setCustomerName("Sunanda");
		cust.setCustomerNumber("9848032919");
		cust.setCustomerEmail("sunanda@gmail.com");
		cust.setCustomerAddress("tambaram,chennai,TN");
		cust.setCustomerStoreName("Pasuparthy");

		Customer cust1 = new Customer();
		cust1.setCustomerName("Sarath Lekha");
		cust1.setCustomerNumber("8989545274");
		cust1.setCustomerEmail("sarathlekha@gmail.com");
		cust1.setCustomerAddress(" 19-12-215/c,Bairagipatteda,Tirupati,517501,AP");
		cust1.setCustomerStoreName("V mart");

		List<Customer> custList = new ArrayList<>();
		custList.add(cust);
		custList.add(cust1);

		String jsonInput = this.converttoJson(custList);

		Mockito.when(customerservice.viewAllCustomers()).thenReturn(custList);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 100).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isNotEqualTo(jsonOutput);

	}

	@Test
	public void testUpdateCustomer() throws Exception {
		String URI = "/api/v1/customer/{customerId}";
		Customer cust1 = new Customer();
		cust1.setCustomerName("Sarath Lekha");
		cust1.setCustomerNumber("8989545274");
		cust1.setCustomerEmail("sarathlekha@gmail.com");
		cust1.setCustomerAddress(" 19-12-215/c,Bairagipatteda,Tirupati,517501,AP");
		cust1.setCustomerStoreName("V mart");
		String jsonInput = this.converttoJson(cust1);

		Mockito.when(customerservice.updateCustomer(Mockito.any(), Mockito.any())).thenReturn(cust1);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 105)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	private String converttoJson(Object customer) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(customer);
	}

}
