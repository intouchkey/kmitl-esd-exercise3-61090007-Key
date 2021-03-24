package kmitl.esd.exercise3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for quotation
 */
class QuotationTest {

	private Customer customer;
	private Quotation quotation;

	/**
	 * Prepare quotation
	 */
	@BeforeEach
	void prepareQuotation() {
		this.customer = new Customer(1L, "key", 25);
		this.quotation = new Quotation(1L, "quote1", customer);
	}

	/**
	 * check if getter and setter for id works
	 */
	@Test
	void checkId() {
		assertEquals(quotation.getId(), 1L);
		this.quotation.setId(2L);
		assertEquals(quotation.getId(), 2L);
	}

	/**
	 * check if getter and setter for reference works
	 */
	@Test
	void checkName() {
		assertEquals(quotation.getReference(), "quote1");
		this.quotation.setReference("quote2");
		assertEquals(quotation.getReference(), "quote2");
	}

	/**
	 * check if getter and setter for price works
	 */
	@Test
	void checkPrice() {
		this.quotation.setPrice(490);
		assertEquals(quotation.getPrice(), 490);
	}

	/**
	 * check if getter and setter for customer works
	 */
	@Test
	void checkCustomer() {
		assertEquals(quotation.getCustomer(), customer);

		Customer newCustomer = new Customer(3L, "mon", 21);
		this.quotation.setCustomer(newCustomer);
		assertEquals(quotation.getCustomer(), newCustomer);
	}
}