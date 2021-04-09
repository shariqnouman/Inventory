package com.cg.inventory.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.inventory.exceptions.ResourceNotFoundException;
import com.cg.inventory.model.Order;
import com.cg.inventory.repositories.OrdersRepository;

/**
 * The OrderServiceImpl class provides access to JPA methods to create, remove,
 * view, and update orders of customer
 * 
 * @author sharique nooman
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersRepository ordersRepository;

	/**
	 * This method returns list of all orders
	 * 
	 * @return list of orders
	 */
	@Override
	public List<Order> viewAllOrders() {
		return ordersRepository.findAll();
	}

	/**
	 * This method takes order details and creates a new order for customer
	 * 
	 * @param order
	 * @return order entity details
	 */
	@Override
	public Order createOrder(Order order) {
		return ordersRepository.saveAndFlush(order);
	}

	/**
	 * This method takes order Id and deletes the order
	 * 
	 * @param orderId
	 */
	@Override
	public void deleteOrder(Long orderId) {
		ordersRepository.deleteById(orderId);
	}

	/**
	 * This method returns orders by searching with specific order Id
	 * 
	 * @param orderId
	 * @return order details if found or else throw exception
	 */
	@Override
	public Order viewOrderById(Long orderId) {
		return ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this Id : " + orderId));
	}

	/**
	 * This method updates order details by searching with order Id
	 * 
	 * @param orderId
	 * @param order
	 * @return order details if found or else throw exception
	 */
	@Override
	public Order updateOrder(Long orderId, Order order) {
		Order existingOrder = ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this Id : " + orderId));
		BeanUtils.copyProperties(order, existingOrder, "orderId");
		return ordersRepository.saveAndFlush(existingOrder);
	}
}
