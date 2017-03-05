package com.customer.test.solution;

import java.util.ArrayList;
import java.util.List;

import com.customer.test.dao.OrderDaoI;
import com.customer.test.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.test.model.Company;
import com.customer.test.model.Order;
import com.customer.test.service.OrderServiceI;

@Service
public class OrderService implements OrderServiceI {

	/*
	 * Use the orderDao to query for orders in the system. You can then filter
	 * the results here to return the proper value according to the method 
	 */
	@Autowired
    OrderDaoI orderDao;
	
	public OrderService() {
		
	}

	@Override
	public List<Order> getOrdersByStatus(Company company, int status) {
		// TODO Auto-generated method stub
		
		List<Order> orders = orderDao.findAll(company);
		List<Order> orderByStatus = new ArrayList<>(); 
		
		for(Order order: orders ){
			
			if(order.getOrderStatus() ==  status ){
				
				orderByStatus.add(order);
			}
		}
		
		return orderByStatus;
	}

	
	@Override
	public double getTotalCostOfOrder(Order order) {
		
		List<OrderItem> orderItems = order.getOrderItems();
		double cost = 0.0;
		
		for(OrderItem orderItem : orderItems){
			
			cost +=  orderItem.getUnitPrice()*orderItem.getQuantityOrdered() + orderItem.getTax();
		}
		return cost;
	}

}
