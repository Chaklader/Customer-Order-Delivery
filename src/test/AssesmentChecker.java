package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.customer.test.dao.CompanyDaoI;
import com.customer.test.dao.DBInit;
import com.customer.test.dao.PerformanceDataDaoI;
import com.customer.test.model.Company;
import com.customer.test.model.Order;
import com.customer.test.service.CustomerServiceI;
import com.customer.test.service.OrderServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AssesmentChecker {
	@Autowired DBInit dbInit;
	@Autowired CustomerServiceI customerService;
	@Autowired OrderServiceI orderService;
	@Autowired PerformanceDataDaoI performanceDao;

	@Autowired CompanyDaoI companyDao;
	Company company;

	@Before
	public void setUp() throws IOException {
		dbInit.setupDB(true);
		company = companyDao.find(1);
	}

	
	@Test
	public void performanceDaoTotalItemsTest() {

		int count = performanceDao.getTotalItemsOrderedForCompany(company);
		System.out.println("count = " +count);
		assertTrue("Expected 8 items ordered. found "+count,
				count == 8);
	}

	@Test
	public void performanceDaoSalesTest() {
		assertTrue("Incorrect total",
				performanceDao.getTotalSalesAmountForCompany(company) == 473.37);
	}

	@Test
	public void orderServiceByStatusTest() {
		List<Order> orders = orderService.getOrdersByStatus(company, 1);
		assertTrue("Data should be returned.", orders != null);
		assertTrue("There are three orders with status 1 vs " + orders.size(),
				orders.size() == 3);

		orders = orderService.getOrdersByStatus(company, 2);
		assertTrue("There are two orders with status 2",
				orders.size() == 2);
	}

	@Test
	public void orderServiceTotalCostTest() {
		List<Order> orders = orderService.getOrdersByStatus(company, 2);
		double total =
			orderService.getTotalCostOfOrder(orders.get(0)) + orderService.getTotalCostOfOrder(orders.get(1));
		assertTrue("44.27 + 30.25 + 3.10",
				total == 77.62);
	}

	@Test
	public void customerServiceTest() {
		Map<Integer, List<Order>> orderMap = customerService.getOrdersForCustomer(company);

		assertTrue("Data should be returned", orderMap!=null);
		assertTrue("There should be orders for 3 customers", orderMap.keySet().size()==3);

		//Adam Aaronson's orders
		List<Order> orders = orderMap.get(1);
		assertTrue("1 order expected for Adam Aaronson. " + orders.size() + " found",
				orders.size()==1);

		assertTrue(orders.get(0).getOrderNumber().equals("TESTORDER123"));
		assertTrue(orders.get(0).getOrderItems().get(0).getUnitPrice() == 100);

		orders = orderMap.get(2);
		assertTrue("2 orders expected for Billy Blanks. " + orders.size() + " found",
				orders.size()==2);

		assertTrue(orderService.getTotalCostOfOrder(orders.get(0)) == 33.35);
		assertTrue(orders.get(0).getCustomer().getCustomerName().equals("Billy Blanks"));

		orders = orderMap.get(3);
		assertTrue(orders.get(0).getOrderItems().size()==1);
		assertTrue(orders.get(1).getOrderItems().size()==1);
	}

}
