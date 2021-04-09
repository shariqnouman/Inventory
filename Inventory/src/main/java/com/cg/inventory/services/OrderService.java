package com.cg.inventory.services;

import java.util.List;

import com.cg.inventory.model.Order;

/**
 * This is an interface for OrderService class, it has all the abstract methods
 * 
 * @author sharique nooman
 *
 */
public interface OrderService {

	/**
	 * This method returns list of all orders
	 * 
	 * @return list of orders
	 */
	List<Order> viewAllOrders();

	/**
	 * This method takes order details and creates a new order for customer
	 * 
	 * @param order
	 * @return order entity details
	 */
	Order createOrder(Order order);

	/**
	 * This method takes order Id and deletes the order
	 * 
	 * @param orderId
	 */
	void deleteOrder(Long orderId);

	/**
	 * This method returns orders by searching with specific order Id
	 * 
	 * @param orderId
	 * @return order details if found or else throw exception
	 */
	Order viewOrderById(Long orderId);

	/**
	 * This method updates order details by searching with order Id
	 * 
	 * @param orderId
	 * @param order
	 * @return order details if found or else throw exception
	 */
	Order updateOrder(Long orderId, Order order);

}