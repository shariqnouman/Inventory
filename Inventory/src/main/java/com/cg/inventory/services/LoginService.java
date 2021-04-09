package com.cg.inventory.services;

import com.cg.inventory.model.Login;

/**
 * This is an interface for LoginService class, it has all the abstract
 * methods
 * 
 * @author usha Nanga
 *
 */
public interface LoginService {

	/**
	 * This method returns login data by searching with specific user Id
	 * 
	 * @param userId
	 * @return Login object
	 */
	Login findByID(String userId);

	/**
	 * This method takes login details and creates a new record for admin
	 * 
	 * @param data
	 * @return Login object
	 */
	Login createdata(Login data);

	/**
	 * This method updates login details by searching with user Id
	 * 
	 * @param data
	 * @return Login object
	 */
	Login update(Login data);

}