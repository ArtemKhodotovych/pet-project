package pet.customer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pet.customer.web.model.OrderStatusEnum;
import pet.customer.web.model.OrderType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Order entities
 */
@Getter
@Setter
@Entity(name = "order_table")
@NoArgsConstructor
public class Order extends BaseEntity {

    /**
     * The constructor and builder
     * @see Order#Order(UUID, Long, Timestamp, Timestamp, Customer, OrderStatusEnum, String, OrderType)
     * @see Order#Order()
     * @see Order#builder()
     * @param id - id of entity
     * @param version - entity data version
     * @param createdDate - date of creation
     * @param lastModifiedDate - date of last modification
     * @param customer - order's customer
     * @param orderStatus - status of order
     * @param description- free text
     */
    @Builder
    public Order(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, Customer customer,
                 OrderStatusEnum orderStatus, String description, OrderType orderType) {
        super(id, version, createdDate, lastModifiedDate);
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.description = description;
        this.orderType = orderType;
    }

    @ManyToOne
    private Customer customer;
    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
    private OrderType orderType = OrderType.UNUSUAL;
    private String description;

}
