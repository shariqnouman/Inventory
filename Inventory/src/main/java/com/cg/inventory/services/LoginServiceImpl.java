package com.cg.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.inventory.model.Login;
import com.cg.inventory.repositories.LoginRepository;

/**
 * The LoginServiceImpl class provides access to JPA methods
 * 
 * @author Usha Nanga
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	/**
	 * This method returns login data by searching with specific user Id
	 * 
	 * @param userId
	 * @return Login object
	 */
	@Override
	public Login findByID(String userId) {
		return loginRepository.findByID(userId);
	}

	/**
	 * This method takes login details and creates a new record for admin
	 * 
	 * @param data
	 * @return Login object
	 */
	@Override
	public Login createdata(Login data) {
		return loginRepository.saveAndFlush(data);
	}

	/**
	 * This method updates login details by searching with user Id
	 * 
	 * @param data
	 * @return Login object
	 */
	@Override
	public Login update(Login data) {
		return loginRepository.saveAndFlush(data);
	}

}
