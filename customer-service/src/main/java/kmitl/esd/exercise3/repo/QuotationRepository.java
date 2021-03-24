package kmitl.esd.exercise3.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kmitl.esd.exercise3.model.Quotation;

/**
 * Persistence for quotations
 */
public interface QuotationRepository extends CrudRepository<Quotation, Long> {

	/**
	 * Find quotes by date/time
	 *
	 * @param creationDateTime of quote to find
	 * @return quotes with creation date time after/equal the param date time
	 */
	@Query("select q from Quotation q where q.creationDateTime >= :creationDateTime")
	List<Quotation> findAllWithCreationDateTimeAfter(@Param("creationDateTime") LocalDateTime creationDateTime);

	/**
	 * Find quotes by customer id
	 *
	 * @param customerId
	 * @return quotes with that customer id
	 */
	List<Quotation> findAllByCustomerId(@Param("customerId") Long customerId);
}
