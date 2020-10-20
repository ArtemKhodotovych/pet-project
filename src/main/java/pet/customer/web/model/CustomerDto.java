package pet.customer.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * customer dto
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {

    /**
     * The constructor
     * @see CustomerDto#CustomerDto(UUID, Integer, OffsetDateTime, OffsetDateTime, String, Set)
     * @see CustomerDto#CustomerDto()
     * @param id - customer id
     * @param version - customer data version
     * @param createdDate - date of creation
     * @param lastModifiedDate - date of last data update
     * @param customerName - free text customer name
     * @param orders - customer's orders
     */
    @Builder
    public CustomerDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                       String customerName, Set<OrderDto> orders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.orders = orders;
    }

    private String customerName;
    private Set<OrderDto> orders;

}
