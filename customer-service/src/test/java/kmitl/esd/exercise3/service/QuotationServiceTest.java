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
import kmitl.esd.exercise3.model.Quotation;
import kmitl.esd.exercise3.repo.QuotationRepository;

/**
 * Test for quotation service
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class QuotationServiceTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private QuotationRepository quotationRepository;

	/**
	 * Prepare entities before test
	 */
	@Before
	public void prepare() throws Exception {
		Customer customer = new Customer(43L,"key",34);
		Quotation quotation = new Quotation(23L, "quote1", customer);
		entityManager.persist(customer);
		entityManager.persist(quotation);
	}

	/**
	 * Delete entities after test
	 */
	@After
	public void tearDown() throws Exception {
		quotationRepository.deleteAll();
	}

	/**
	 * Function to test getting quotations
	 */
	@Test
	public void getAllQuotations() {
		List<Quotation> quotations = quotationService.getAllQuotations();

		Assert.assertEquals(quotations.size(), 1);
	}

	/**
	 * Function to test saving quotation
	 */
	@Test
	public void saveQuotation() {
		Customer customer = new Customer(43L,"key",34);
		Quotation quotation = new Quotation(25L, "quote2", customer);
		quotationService.saveOrUpdate(quotation);

		List<Quotation> quotations = quotationService.getAllQuotations();
		Assert.assertEquals(quotations.size(), 2);
	}

	/**
	 * Function to test deleting quotation
	 */
	@Test
	public void deleteQuotation() {
		quotationService.delete(23L);

		List<Quotation> quotations = quotationService.getAllQuotations();
		Assert.assertEquals(quotations.size(), 0);
	}
}