package com.customer.test.solution;

import javax.sql.DataSource;

import com.customer.test.dao.PerformanceDataDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.customer.test.dao.GenericDao;
import com.customer.test.model.Company;


@Repository
public class PerformanceDataDao extends GenericDao implements PerformanceDataDaoI {
	
	@Override
	public double getTotalSalesAmountForCompany(Company company) {
		
		String sql = "SELECT SUM(quantity_ordered*unit_price + tax) AS totalSales "
				+ "FROM order_items, orders "
				+ "WHERE order_items.order_id = orders.order_id AND orders.company_id = ?";
		
		return jdbcTemplateObject.queryForObject(sql, Double.class, new Object[] {company.getCompanyId()});
	}

	
	@Override
	public int getTotalItemsOrderedForCompany(Company company) {

		String sql = "SELECT SUM(quantity_ordered) AS totalItems FROM order_items, orders "
				+ "WHERE order_items.order_id = orders.order_id "
				+ "AND orders.company_id = ?";

		return jdbcTemplateObject.queryForObject(sql, Integer.class, new Object[] {company.getCompanyId()});	
	}

	public PerformanceDataDao() {
		
	}

	@Autowired
	public PerformanceDataDao(DataSource dataSource) {

		super(dataSource);
	}

}
