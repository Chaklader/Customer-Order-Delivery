package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.customer.test.dao.CompanyDaoI;
import com.customer.test.dao.DBInit;
import com.customer.test.dao.OrderDaoI;
import com.customer.test.model.Company;
import com.customer.test.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class IntegratedTest {
	@Autowired DBInit dbInit;
	@Autowired CompanyDaoI companyDao;
	@Autowired OrderDaoI orderDao;

	Company company;
	
	@Before
	public void setUp() throws Exception {
		dbInit.setupDB(true);
		company = companyDao.find(1);
	}

	@Test
	public void testOrdersDao() { 
		List<Order> orders = orderDao.findAll(company);
		assertTrue(orders!=null);
		assertTrue(orders.size()==5);
		Order order = null;
		for(Order orderIter : orders) {
			if(orderIter.getOrderNumber().equals("TESTORDER123")) {
				order = orderIter;
			}
		}
		assertTrue(order!=null);
		assertTrue(order.getOrderStatus() == 1);
		assertTrue(order.getOrderItems().get(0).getListingName().equals("Cowboy Boots (Red)"));
		assertTrue(order.getOrderItems().get(0).getListingSku().equals("CWBB-1"));
		assertTrue("Expected 1: "+order.getOrderItems().get(0).getQuantityOrdered(), 
				order.getOrderItems().get(0).getQuantityOrdered() == 3);
		assertTrue(order.getOrderItems().get(0).getUnitPrice() == 100d);
		assertTrue(order.getOrderItems().get(0).getTax() == .75d);
		
		assertTrue(order.getCompany().getCompanyName().equals("Test Company"));
		assertTrue(order.getCompany().getContactName().equals("Johnny Ceo"));
		assertTrue(order.getCompany().getContactEmail().equals("johnny@testcompany.com"));
	}

}
