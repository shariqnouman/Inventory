package com.cg.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.inventory.model.Customer;

/**
 * This is an interface which defines CRUD methods for Customer JPA repository
 * 
 * @author sharique nooman
 *
 */
public interface CustomersRepository extends JpaRepository<Customer, Long> {

}
