package com.cg.inventory.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginTest {
Login  data=new Login();
	@Test     
	void testUserId() {
	data.setUserId("UshaNanga");
	assertNotNull(data.getUserId());
	}
	@Test
	void testPassword() {
	data.setPassword("Sai@1999Nanga");
	assertNotNull(data.getPassword());
	}
	@Test
	void testType() {
	data.setType("admin");
	assertNotNull(data.getType());
	}
	@Test
	void testNullType()
	{
		data.setType(null);
		assertNull(data.getType());
	}
	@Test
	void testNullPassword()
	{
		data.setPassword(null);
		assertNull(data.getPassword());
	}
	@Test
	void testNullUserid()
	{
		data.setUserId(null);
		assertNull(data.getUserId());
	}
	
	@Test
	void testData()
	{
		data.setUserId("UshaNanga");
		data.setType("admin");
		data.setPassword("Sai@1999Nanga");
		assertNotNull(data.getType());
		assertNotNull(data.getUserId());
		assertNotNull(data.getPassword());
	}
}
