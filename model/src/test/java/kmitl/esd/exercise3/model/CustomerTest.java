package kmitl.esd.exercise3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for customer
 */
class CustomerTest {

	private Customer customer;

	/**
	 * Prepare customer
	 */
	@BeforeEach
	void prepareCustomer() {
		this.customer = new Customer(1L, "key", 25);
	}

	/**
	 * check if getter and setter for id works
	 */
	@Test
	void checkId() {
		assertEquals(customer.getId(), 1L);
		this.customer.setId(2L);
		assertEquals(customer.getId(), 2L);
	}

	/**
	 * check if getter and setter for name works
	 */
	@Test
	void checkName() {
		assertEquals(customer.getName(), "key");
		this.customer.setName("mel");
		assertEquals(customer.getName(), "mel");
	}

	/**
	 * check if getter and setter for age works
	 */
	@Test
	void checkAge() {
		assertEquals(customer.getAge(), 25);
		this.customer.setAge(35);
		assertEquals(customer.getAge(), 35);
	}

	/**
	 * check if getter and setter for phoneNumber works
	 */
	@Test
	void checkPhoneNumber() {
		this.customer.setPhoneNumber("(010)123-1234");
		assertEquals(customer.getPhoneNumber(), "(010)123-1234");
	}

	/**
	 * check if getter and setter for email works
	 */
	@Test
	void checkEmail() {
		this.customer.setEmail("a@gmail.com");
		assertEquals(customer.getEmail(), "a@gmail.com");
	}
}