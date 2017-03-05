package com.customer.test.dao;

import java.util.List;

import com.customer.test.model.Order;
import com.customer.test.model.OrderItem;

public interface OrderItemDaoI {
	/**
	 * Find all Order Items for the input Order
	 * @param order
	 * @return
	 */
	public List<OrderItem> findAll(Order order);
}
