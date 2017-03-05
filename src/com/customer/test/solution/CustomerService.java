package com.customer.test.solution;

import com.customer.test.dao.CustomerDaoI;
import com.customer.test.dao.OrderDaoI;
import com.customer.test.model.Company;
import com.customer.test.model.Order;
import com.customer.test.service.CustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements CustomerServiceI {

	/*
	 * Use one or both DAO objects here to query for customer and order data,
	 * and to return the lists in the format specified by the method. You may
	 * only need one. Review the documentation and Model to decide
	 */
	
	@Autowired OrderDaoI orderDao;	
	@Autowired
    CustomerDaoI customerDao;
	
	public CustomerService() {

	}
	
	
	@Override
	public Map<Integer, List<Order>> getOrdersForCustomer(Company company) {
		
		List<Order> orders = orderDao.findAll(company);
		Map<Integer, List<Order>> map = new HashMap<>();
				
		for(Order order: orders){
			
			int customerId = order.getCustomerId();
			if(!map.containsKey(customerId)){
				
				map.put(customerId, new ArrayList<Order>());
			}
			
			map.get(customerId).add(order);
		}
			
		return map; 
	}

}
