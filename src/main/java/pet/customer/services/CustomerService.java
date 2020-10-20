package pet.customer.services;

import pet.customer.web.model.CustomerDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Customer service interface
*/
public interface CustomerService {

    List<CustomerDto> listCustomers();

    CustomerDto findCustomerById(UUID customerId);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteById(UUID customerId);

}
