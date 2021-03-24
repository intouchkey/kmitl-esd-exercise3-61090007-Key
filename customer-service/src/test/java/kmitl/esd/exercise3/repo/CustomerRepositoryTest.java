package kmitl.esd.exercise3.repo;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import kmitl.esd.exercise3.model.Customer;

/**
 * Test for customer repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Delete entities after test
	 */
	@After
	public void tearDown() throws Exception {
		customerRepository.deleteAll();
	}

	/**
	 * Test finding a customer by id
	 */
	@Test
	public void testFindById() {
		Customer customer = new Customer(20L,"key",34);
		entityManager.persist(customer);

		Optional<Customer> customerOptional = customerRepository.findById(20L);

		Assert.assertEquals(customerOptional.get().getName(), "key");
	}
}