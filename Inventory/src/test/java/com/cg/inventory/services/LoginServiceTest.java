package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.InventoryApplication;
import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Login;
import com.cg.inventory.repositories.LoginRepository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=InventoryApplication.class)
class LoginServiceTest {
	  @MockBean
	   private LoginRepository repostry;

	    @Autowired
	    private LoginServiceImpl service;
	    	    
	@Test
	 void validateUserData() throws ResourceNotFoundException
	{
		Login data=new Login();
		data.setUserId("Usha");
		data.setPassword("ushareddy");
		data.setType("admin");
       Mockito.when(repostry.findByID("Usha")).thenReturn(data);
        assertThat(service.findByID("Usha")).isEqualTo(data);
	}


}
