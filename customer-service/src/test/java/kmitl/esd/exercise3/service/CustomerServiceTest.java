package kmitl.esd.exercise3.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import kmitl.esd.exercise3.model.Customer;
import kmitl.esd.exercise3.repo.CustomerRepository;

/**
 * Test for customer service
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Prepare entities before test
	 */
	@Before
	public void prepare() throws Exception {
		Customer customer1 = new Customer(20L,"key",34);
		Customer customer2 = new Customer(25L,"boss",32);
		entityManager.persist(customer1);
		entityManager.persist(customer2);
	}

	/**
	 * Delete entities after test
	 */
	@After
	public void tearDown() throws Exception {
		customerRepository.deleteAll();
	}

	/**
	 * Function to test getting customers
	 */
	@Test
	public void getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();

		Assert.assertEquals(customers.size(), 2);
	}

	/**
	 * Function to test saving customer
	 */
	@Test
	public void saveCustomer() {
		Customer customer2 = new Customer(30L,"ratt",38);
		customerService.createOrUpdate(customer2);

		List<Customer> customers = customerService.getAllCustomers();
		Assert.assertEquals(customers.size(), 3);
	}

	/**
	 * Function to test deleting customer
	 */
	@Test
	public void deleteCustomer() {
		customerService.deleteCustomer(20L);

		List<Customer> customers = customerService.getAllCustomers();
		Assert.assertEquals(customers.size(), 1);
	}
}