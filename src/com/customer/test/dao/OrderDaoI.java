package com.customer.test.dao;

import java.util.List;

import com.customer.test.model.Company;
import com.customer.test.model.Order;

public interface OrderDaoI {
	
	public Order find(Company company, int orderId);
	
	public List<Order> findAll(Company company);
	
}
