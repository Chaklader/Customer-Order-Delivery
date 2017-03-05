package com.customer.test.dao;

import java.util.List;

import javax.sql.DataSource;

import com.customer.test.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.customer.test.model.Order;

@Repository
public class OrderItemDao extends GenericDao implements OrderItemDaoI {

	public OrderItemDao() {
	}

	@Autowired
	public OrderItemDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<OrderItem> findAll(Order order) {
		String sql = "SELECT * from order_items WHERE order_id = ? ";
		return jdbcTemplateObject.query(sql, new Object[] {order.getOrderId()}, 
				new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
	}

}
