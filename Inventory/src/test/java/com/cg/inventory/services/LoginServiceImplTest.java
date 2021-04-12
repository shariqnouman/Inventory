package com.cg.inventory.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Login;
import com.cg.inventory.repositories.LoginRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LoginServiceImpl.class)
public class LoginServiceImplTest {

	@MockBean
	private LoginRepository repostry;

	@Autowired
	private LoginServiceImpl service;

	@Test
	public void validateUserData() throws ResourceNotFoundException {
		Login data = new Login();
		data.setUserId("Usha");
		data.setPassword("ushareddy");
		data.setType("admin");
		Mockito.when(repostry.findByID("Usha")).thenReturn(data);
		assertThat(service.findByID("Usha")).isEqualTo(data);
	}
}
