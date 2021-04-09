package com.cg.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.inventory.model.Order;

/**
 * This is an interface which defines CRUD methods for order JPA repository
 * 
 * @author sharique nooman
 *
 */
public interface OrdersRepository extends JpaRepository<Order, Long> {

}
