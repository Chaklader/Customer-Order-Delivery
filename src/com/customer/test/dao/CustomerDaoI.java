package com.customer.test.dao;

import java.util.List;

import com.customer.test.model.Company;
import com.customer.test.model.Customer;

public interface CustomerDaoI {
	/**
	 * 
	 * @param company
	 * @param customerId
	 * @return Customer for the input customer ID and given company
	 */
	public Customer find(Company company, int customerId);
	
	/**
	 * 
	 * @param company
	 * @return a list of all customers that do business with the input company
	 */
	public List<Customer> findAll(Company company);
}
