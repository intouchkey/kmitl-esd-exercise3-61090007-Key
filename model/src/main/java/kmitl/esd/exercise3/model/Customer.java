package kmitl.esd.exercise3.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer model
 */
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Customer {
    /**
     * id of the customer - primary key
     */
    @Id
    @Column()
    private Long id;

    /**
     * name of the customer
     */
    @Column() @NotNull @Size(min = 3, max = 240)
    private String name;

    /**
     * age of the customer
     */
    @Column() @Min(18) @Max(120)
    private Integer age;

    /**
     * customer's phone number, with REGEX to validate
     */
    @Column() @Pattern(regexp = "\\(?\\d{0,3}\\)?\\d{3}-\\d{4}")
    private String phoneNumber;

    /**
     * customer's email, with REGEX to validate
     */
    @Column() @Pattern(regexp = ".+@.*\\..*")
    private String email;

    /**
     * creation time
     */
    @Column() @Past
    private LocalDateTime creationDateTime;

    /**
     * Constructor for creating customer
     * @param id customer id
     * @param name name of the customer
     * @param age age in years
     */
    public Customer(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor for creating customer
     * @param name name of the customer
     * @param age age in years
     */
    public void Constructor(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
