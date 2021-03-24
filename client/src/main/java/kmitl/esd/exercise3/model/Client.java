package kmitl.esd.exercise3.model;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Client used for testing api
 */
@SpringBootApplication
public class Client {

	private static final Logger log = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Client.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8089"));
		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * Main entry point for the client
	 * Try calling GET for customers
	 */
	@Bean
	public CommandLineRunner runGetAllCustomers(RestTemplate restTemplate) throws Exception {
		return args -> {
			String response = callGetAllCustomers(restTemplate);
			log.info(String.format("Get all customers: " + response));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling POST for customers
	 */
	@Bean
	public CommandLineRunner runCreateCustomer(RestTemplate restTemplate) throws Exception {
		return args -> {
			callCreateOrUpdateCustomer(restTemplate, 3L, "yay", 49);
			log.info(String.format("Create new customer: " + 3L));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling PUT for customers
	 */
	@Bean
	public CommandLineRunner runUpdateCustomer(RestTemplate restTemplate) throws Exception {
		return args -> {
			callCreateOrUpdateCustomer(restTemplate, 3L, "yay2", 49);
			String response = callGetAllCustomers(restTemplate);

			log.info(String.format("Update and get all customers: " + response));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling DELETE for customers
	 */
	@Bean
	public CommandLineRunner runDeleteCustomer(RestTemplate restTemplate) throws Exception {
		return args -> {
			callDeleteCustomer(restTemplate, "3");
			String response = callGetAllCustomers(restTemplate);

			log.info(String.format("Delete and get all customers: " + response));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling POST for quotation
	 */
	@Bean
	public CommandLineRunner runCreateQuotation(RestTemplate restTemplate) throws Exception {
		return args -> {
			callCreateOrUpdateCustomer(restTemplate, 5L, "yay", 49);
			callCreateOrUpdateQuotation(restTemplate, 10L, "quote1", 5L, "yay", 49);
			log.info(String.format("Create new quotation: " + 10L));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling GET for quotations
	 */
	@Bean
	public CommandLineRunner runGetAllQuotations(RestTemplate restTemplate) throws Exception {
		return args -> {
			String response = callGetAllQuotations(restTemplate);
			log.info(String.format("Get all quotations: " + response));
		};
	}

	/**
	 * Main entry point for the client
	 * Try calling DELETE for quotations
	 */
	@Bean
	public CommandLineRunner runDeleteQuotation(RestTemplate restTemplate) throws Exception {
		return args -> {
			callDeleteQuotation(restTemplate, "10");
			String response = callGetAllQuotations(restTemplate);

			log.info(String.format("Delete and get all quotations: " + response));
		};
	}

	/**
	 * Call get all customers
	 */
	String callGetAllCustomers(RestTemplate restTemplate) {
		StringBuffer url = new StringBuffer("http://localhost:8000/customer");
		String respString = restTemplate.getForObject(url.toString(), String.class);

		return respString;
	}

	/**
	 * Call create/update customer
	 */
	void callCreateOrUpdateCustomer(RestTemplate restTemplate, Long id, String name, int age) throws JSONException {
		JSONObject data = new JSONObject();
		data.put("id", id);
		data.put("name", name);
		data.put("age", age);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reqBody = new HttpEntity<>(data.toString(), headers);

		String url = "http://localhost:8000/customer";
		restTemplate.exchange(url, HttpMethod.POST, reqBody, Customer.class);
	}

	/**
	 * Call delete customer
	 */
	void callDeleteCustomer(RestTemplate restTemplate, String id) {
		String url = "http://localhost:8000/customer/" + id;

		restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
	}

	/**
	 * Call get all quotations
	 */
	String callGetAllQuotations(RestTemplate restTemplate) {
		StringBuffer url = new StringBuffer("http://localhost:8000/quotation");
		String respString = restTemplate.getForObject(url.toString(), String.class);

		return respString;
	}

	/**
	 * Call create/update quotation
	 */
	void callCreateOrUpdateQuotation(RestTemplate restTemplate, Long id, String reference, Long customerId, String customerName,
			Integer customerAge) throws JSONException {
		JSONObject quotationData = new JSONObject();
		quotationData.put("id", id);
		quotationData.put("reference", reference);

		JSONObject customerData = new JSONObject();
		customerData.put("id", customerId);
		customerData.put("name", customerName);
		customerData.put("age", customerAge);

		quotationData.put("customer", customerData);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reqBody = new HttpEntity<>(quotationData.toString(), headers);

		String url = "http://localhost:8000/quotation";
		restTemplate.exchange(url, HttpMethod.POST, reqBody, Quotation.class);
	}

	/**
	 * Call delete quotation
	 */
	void callDeleteQuotation(RestTemplate restTemplate, String id) {
		String url = "http://localhost:8000/quotation/" + id;

		restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
	}
}
