
package kmitl.esd.exercise3.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kmitl.esd.exercise3.model.Customer;
import kmitl.esd.exercise3.service.CustomerService;

/**
 * API for customers (RestController)
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Function to get all of the customers
     * Can be called using GET method
     *
     * @return list of customers
     */
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Function to get customer by id
     * Can be called using GET method
     *
     * @param id of the customer
     * @return list of customers
     */
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Function to save customer
     * Can be called using POST method
     *
     * @param customer
     */
    @PostMapping("/customer")
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.createOrUpdate(customer);
    }

    /**
     * Function to delete customer
     * Can be called using DELETE method
     *
     * @param id of the customer
     */
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}