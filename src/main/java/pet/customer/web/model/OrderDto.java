package pet.customer.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
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
public class OrderDto extends BaseItem {

    /**
     * The constructor
     * @see OrderDto#OrderDto(UUID, Integer, OffsetDateTime, OffsetDateTime, OrderStatusEnum, String, OrderType)
     * @see OrderDto#OrderDto()
     * @param id - customer id
     * @param version - customer data version
     * @param createdDate - date of creation
     * @param lastModifiedDate - date of last data update
     * @param orderStatus - status of order
     * @param description - free text
     */
    @Builder
    public OrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                    OrderStatusEnum orderStatus, String description, OrderType orderType) {
        super(id, version, createdDate, lastModifiedDate);
        this.orderStatus = orderStatus;
        this.description = description;
        this.orderType = orderType;
    }

    private OrderType orderType;
    private OrderStatusEnum orderStatus;
    private String description;

}
