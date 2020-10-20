package pet.customer.repositories;

import pet.customer.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Order CRUD repository
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * search order by particular id
     * @param id - order id
     * @return order
     */
    Order findOrderById(UUID id);

}
