package com.customer.test.service;

import java.util.List;
import java.util.Map;

import com.customer.test.model.Company;
import com.customer.test.model.Order;

public interface CustomerServiceI {
	
	/**
	 * Get all orders for a company's customers
	 * @param company
	 * @return a Map with each key representing the customer ID. The key references a 
	 * list of Orders for that particular customer. If a customer has no orders, they are not added
	 * to the return Map
	 */
	Map<Integer, List<Order>> getOrdersForCustomer(Company company);
}
