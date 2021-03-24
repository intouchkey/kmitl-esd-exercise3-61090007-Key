package kmitl.esd.exercise3.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kmitl.esd.exercise3.model.Customer;

/**
 * Persistence for customers
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	/**
	 * Find customers by name and ordered by name
	 *
	 * @param name of customers to find
	 * @return customers with the name or empty list
	 */
	List<Customer> findByNameOrderByName(String name);
}
