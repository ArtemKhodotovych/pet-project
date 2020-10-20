package pet.customer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Customer entities
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer extends BaseEntity {

    /**
     * The constructor and builder
     * @see Customer#Customer(UUID, Long, Timestamp, Timestamp, String, Set)
     * @see Customer#Customer()
     * @see Customer#builder()
     * @param id - id of entity
     * @param version - entity data version
     * @param createdDate - date of creation
     * @param lastModifiedDate - date of last modification
     * @param customerName - free text
     * @param orders - customer's orders
     */
    @Builder
    public Customer(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerName,
                    Set<Order> orders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.orders = orders;
    }

    private String customerName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Set<Order> orders  = new HashSet<>();

}

