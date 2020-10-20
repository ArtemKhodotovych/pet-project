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
 * Updatable order status dto
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatusUpdate extends BaseItem {

    /**
     * The constoructor
     * @see OrderStatusUpdate#OrderStatusUpdate(UUID, Integer, OffsetDateTime, OffsetDateTime, UUID, String)
     * @see OrderStatusUpdate#OrderStatusUpdate()
     * @param id - customer id
     * @param version - customer data version
     * @param createdDate - date of creation
     * @param lastModifiedDate - date of last data update
     * @param orderId - existing order's id
     * @param orderStatus - new status of order
     */
    @Builder
    public OrderStatusUpdate(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                             UUID orderId, String orderStatus) {
        super(id, version, createdDate, lastModifiedDate);
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    private UUID orderId;
    private String orderStatus;

}
