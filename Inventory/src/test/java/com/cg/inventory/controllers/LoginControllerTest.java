package com.cg.inventory.controllers;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.inventory.InventoryApplication;
import com.cg.inventory.model.Login;
import com.cg.inventory.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InventoryApplication.class)
public class LoginControllerTest {
	 @Autowired
	   private MockMvc mockMvc;
	 @MockBean
	 private LoginService service;
	
	//This testValidateUser method for validate user
	@Test
	public void testValidateUser() throws Exception {
		final String uri= "/api/v1/login/Login1/{userId}/{password}";
	        Login data=new Login();
	        data.setUserId("UshaNanga");
	        data.setPassword("Sai@1999Nanga");
	        data.setEmailId("nangausha@gmail.com");
	        String jsonInput = this.converttoJson(data);
	        Mockito.when(service.findByID((String) (Mockito.any()))).thenReturn(data);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri,"UshaNanga","Sai@1999Nanga","nangausha@gmail.com").accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
          assertThat("sucess login").isNotEqualTo(jsonOutput);
  
	    }
	 private String converttoJson(Object data) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(data);
	    }
	
	 @Test
	    public void testcreatedata() throws Exception{
		  String URI = "/validate/register";
		  Login data=new Login();
		  data.setUserId("UshaReddy");
	        data.setPassword("Sai@1999Nanga");
	        data.setType("admin");
		  String jsonInput = this.converttoJson(data);

		  Mockito.when(service.createdata(Mockito.any(Login.class))).thenReturn(data);
		  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isNotEqualTo(jsonOutput);
	 
	 }
	 
	 @Test
	   public void testupdatedata() throws Exception{
		  String URI = "/validate/register";
		  Login data=new Login();
		  data.setUserId("UshaReddy");
	        data.setPassword("Sai@1999Nanga");
	        data.setType("admin");
		  String jsonInput = this.converttoJson(data);

		  Mockito.when(service.createdata(Mockito.any(Login.class))).thenReturn(data);
		  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isNotEqualTo(jsonOutput);
	 
	 }


}




