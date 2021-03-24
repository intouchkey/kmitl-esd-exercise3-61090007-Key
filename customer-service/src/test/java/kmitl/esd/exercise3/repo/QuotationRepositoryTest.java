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
import kmitl.esd.exercise3.model.Quotation;

/**
 * Test for customer repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class QuotationRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private QuotationRepository quotationRepository;

	/**
	 * Delete entities after test
	 */
	@After
	public void tearDown() throws Exception {
		quotationRepository.deleteAll();
	}

	/**
	 * Test finding a quotation by id
	 */
	@Test
	public void testFindById() {
		Customer customer = new Customer(43L,"key",34);
		Quotation quotation = new Quotation(23L, "quote1", customer);
		entityManager.persist(customer);
		entityManager.persist(quotation);

		Optional<Quotation> quotationOptional = quotationRepository.findById(23L);

		Assert.assertEquals(quotationOptional.get().getReference(), "quote1");
	}
}