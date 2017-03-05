package com.customer.test.service;

import com.customer.test.model.Company;
import com.customer.test.model.Order;

import java.util.List;

public interface OrderServiceI {
	/**
	 * Get all the orders for a company, for the input status
	 * @param company
	 * @param status orderStatus of Order to get
	 * @return a List of Orders that have an order status that matches the input param
	 */
	public List<Order> getOrdersByStatus(Company company, int status);
	
	/**
	 * Get the total cost of an order. The Total cost is defined as the unit price
	 * of all the order items, multiplied by the quantity. The tax for each Order
	 * Item is added into the result.
	 * @param order
	 * @return
	 */
	public double getTotalCostOfOrder(Order order);
}
