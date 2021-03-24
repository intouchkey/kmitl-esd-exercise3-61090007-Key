package kmitl.esd.exercise3.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Quotation model
 */
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Quotation {
	/**
	 * quotation's id - primary key
	 */
	@Id
	@Column
	private Long id;

	/**
	 * quotation name
	 */
	@Column() @NotNull @Size(min = 3, max = 240)
	private String reference;

	/**
	 * a quote must have one customer
	 */
	@ManyToOne @NotNull
	private Customer customer;

	/**
	 * price of the quote
	 */
	@NotNull @Min(0)
	private float price;

	/**
	 * creation time
	 */
	@Column @Past
	private LocalDateTime creationDateTime;

	/**
	 * constraint for temporal field - time that this quote is valid until
	 */
	@Column @Past
	private LocalDateTime validUntilDateTime;

	/**
	 * constraint - time that this quote is valid until
	 */
	@Column @Past
	private LocalDateTime saveDateTime;

	/**
	 * Constructor for creating quotation
	 * @param id customer id
	 * @param reference name of the customer
	 * @param customer age in years
	 */
	public Quotation(Long id, String reference, Customer customer) {
		this.id = id;
		this.reference = reference;
		this.customer = customer;
	}
}
