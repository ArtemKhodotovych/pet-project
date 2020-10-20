package pet.customer.services;

import org.springframework.http.ResponseEntity;
import pet.customer.web.model.OrderDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Order service interface
 */
public interface OrderService {

    ResponseEntity<List> listCustomerOrders(UUID customerId);

    OrderDto placeOrder(UUID customerId, OrderDto orderDto);

    OrderDto getOrderById(UUID orderId);

    void pickupOrder(UUID orderId);

}
