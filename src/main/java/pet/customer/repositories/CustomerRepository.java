package pet.customer.repositories;

import pet.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Customer CRUD repository
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    /**
     * search all customers by partial matching of name
     * @param customerName - free search text
     * @return collection with found customers
     */
    List<Customer> findAllByCustomerNameLike(String customerName);

    /**
     * search customer by particular id
     * @param id - customer id
     * @return customer
     */
    Customer findCustomerById(UUID id);

}
