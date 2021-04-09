package com.cg.inventory.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Login;
@RunWith(SpringRunner.class)
@DataJpaTest
 class LoginRepositoryTest {

		@Autowired
		private  LoginRepository logintest;

		@Autowired
		private TestEntityManager test;
	    
		//This validateUserData for checking for userData  
		@Test
		 void validateUserData() throws ResourceNotFoundException
		{
			boolean flag=false;
			Login data=new Login();
			data.setUserId("UshaNanga");
			data.setPassword("Sai@1999");
			data.setType("admin");
			data.setcontactNumber("8019400183");
			data.setEmailId("nangausha@gmai.com");
			test.persist(data);
			
			Login v;
					v=logintest.findByID(data.getUserId());
						if(v.getUserId().equals("UshaNanga") && v.getPassword().equals(data.getPassword()) && v.getType().equals(data.getType()))
					{
						flag=true;
					}
	    assertTrue(flag);
		}


}
