package kmitl.esd.exercise3.server.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import kmitl.esd.exercise3.model.Customer;
import kmitl.esd.exercise3.model.Quotation;

/**
 * Test for quotation controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuotationControllerTest {

	@LocalServerPort
	int randomServerPort;
	String url="http://localhost:";
	RestTemplate restTemplate = new RestTemplate();

	/**
	 * Function to test if calling /quotation using GET method works
	 */
	@Test
	public void getAllQuotations() throws URISyntaxException {
		final String baseUrl = url + randomServerPort + "/quotation";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	/**
	 * Function to test if calling /quotation using POST method works
	 */
	@Test
	public void saveQuotation() throws URISyntaxException {
		this.saveCustomer();

		final String baseUrl = url + randomServerPort + "/quotation";
		URI uri = new URI(baseUrl);
		Customer customer = new Customer(12L,"key",34);
		Quotation quotation = new Quotation(2L, "quote1", customer);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, quotation, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	/**
	 * Function to test if calling /quotation/{id} using DELETE method works
	 */
	@Test
	public void deleteQuotation() throws URISyntaxException {
		this.saveCustomer();

		final String baseUrl1 = url + randomServerPort + "/quotation";
		URI uri1 = new URI(baseUrl1);
		Customer customer = new Customer(12L,"key",34);
		Quotation quotation = new Quotation(3L, "quote1", customer);
		ResponseEntity<String> result = restTemplate.postForEntity(uri1, quotation, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());

		final String baseUrl = url + randomServerPort + "/quotation/3";
		URI uri2 = new URI(baseUrl);
		ResponseEntity<String> result2 = restTemplate.exchange(uri2, HttpMethod.DELETE, null, String.class);

		Assert.assertEquals(200, result2.getStatusCodeValue());
	}

	/**
	 * Function to save customer for testing
	 */
	private void saveCustomer() throws URISyntaxException {

		final String baseUrl = url + randomServerPort + "/customer";
		URI uri = new URI(baseUrl);
		Customer customer = new Customer(12L,"key",34);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, customer, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}
}