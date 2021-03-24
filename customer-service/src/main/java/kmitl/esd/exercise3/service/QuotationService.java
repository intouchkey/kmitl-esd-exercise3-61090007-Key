package kmitl.esd.exercise3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kmitl.esd.exercise3.model.Customer;
import kmitl.esd.exercise3.model.Quotation;
import kmitl.esd.exercise3.repo.QuotationRepository;

/**
 * Quotation service, business logic for quotation data
 */
@Service
public class QuotationService {
	Logger logger = Logger.getLogger(QuotationService.class.toString());

	/**
	 * Persistence for quotes
	 */
	@Autowired
	QuotationRepository quotationRepository;

	/**
	 * service for customer
	 */
	@Autowired
	CustomerService customerService;

	/**
	 * Function to get all of the quotations
	 *
	 * @return list of quotations
	 */
	public List<Quotation> getAllQuotations() {
		List<Quotation> quotations = new ArrayList<>();
		quotationRepository.findAll().forEach(quotation -> quotations.add(quotation));
		return quotations;
	}

	/**
	 * Get quotations by customer id
	 *
	 * @param customerId
	 * @return list of quotations with that customer id
	 */
	public List<Quotation> getQuotationsByCustomerId(Long customerId) {
		return quotationRepository.findAllByCustomerId(customerId);
	}

	/**
	 * Save or update quotation
	 *
	 * @param quotation to be inserted or updated
	 * @return void
	 */
	public void saveOrUpdate(Quotation quotation) {
		if (quotation.getCustomer() == null || quotation.getCustomer().getId() == null) {
			throw new Error("A Quotation must have a customer");
		}

		Customer customer = customerService.getCustomerById(quotation.getCustomer().getId());
		if (customer == null) {
			throw new Error("Customer not found");
		}

		logger.info("customer: " + customer.toString());

		quotationRepository.save(quotation);
	}

	/**
	 * Delete quotation
	 *
	 * @param quotationId to delete
	 * @return void
	 */
	public void delete(Long quotationId) {
		quotationRepository.deleteById(quotationId);
	}
}
