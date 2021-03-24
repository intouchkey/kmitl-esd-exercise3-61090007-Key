package kmitl.esd.exercise3.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kmitl.esd.exercise3.model.Quotation;
import kmitl.esd.exercise3.service.QuotationService;

/**
 * API for quotations (RestController)
 */
@RestController
public class QuotationController {

	@Autowired
	QuotationService quotationService;

	/**
	 * Function to get all of the quotations
	 * Can be called using GET method
	 *
	 * @return list of quotations
	 */
	@GetMapping("/quotation")
	public List<Quotation> getAllQuotations() {
		return quotationService.getAllQuotations();
	}

	/**
	 * Function to get quotation by customer id
	 * Can be called using GET method
	 *
	 * @param id of the quotation
	 * @return list of quotations
	 */
	@GetMapping("/quotation/{id}")
	public List<Quotation> getQuotationsByCustomerId(@PathVariable("id") Long id) {
		return quotationService.getQuotationsByCustomerId(id);
	}

	/**
	 * Function to save quotation
	 * Can be called using POST method
	 *
	 * @param quotation
	 */
	@PostMapping("/quotation")
	public void saveQuotation(@RequestBody Quotation quotation) {
		quotationService.saveOrUpdate(quotation);
	}

	/**
	 * Function to delete quotation
	 * Can be called using DELETE method
	 *
	 * @param id of the quotation
	 */
	@DeleteMapping("/quotation/{id}")
	public void deleteQuotation(@PathVariable("id") Long id) {
		quotationService.delete(id);
	}
}
