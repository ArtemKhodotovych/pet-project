package pet.customer.services;

import pet.customer.domain.Customer;
import pet.customer.repositories.CustomerRepository;
import pet.customer.web.mappers.CustomerMapper;
import pet.customer.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Customer service implementation
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * get all customers
     * @see CustomerServiceImpl#listCustomers() 
     * @return collection of customers
     */
    @Override
    public List<CustomerDto> listCustomers() {
        log.debug("Listing Customers");

        List<CustomerDto> customersDto;
        List<Customer> customers = customerRepository.findAll();;

        customersDto = customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());

        return customersDto;
    }

    /**
     * search customer by id
     * @see CustomerServiceImpl#findCustomerById(UUID) 
     * @param customerId - customer id
     * @return found customer
     * @throws ResponseStatusException
     */
    @Override
    public CustomerDto findCustomerById(UUID customerId) {
        log.debug("Finding Customer by id: " + customerId);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            log.debug("Found CustomerId: " + customerId);
            return customerMapper.customerToCustomerDto(customerOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + customerId);
        }
    }

    /**
     * save new customer
     * @see CustomerServiceImpl#saveCustomer(CustomerDto) 
     * @param customerDto - new customer for saving
     * @return saved customer
     */
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        return customerMapper.customerToCustomerDto(customerRepository.save(customer));
    }

    /**
     * update existing customer
     * @see CustomerServiceImpl#updateCustomer(UUID, CustomerDto) 
     * @param customerId - customer id
     * @param customerDto - updatable customer data
     * @throws ResponseStatusException
     */
    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        customerOptional.ifPresentOrElse(customer -> {
            customer.setCustomerName(customerDto.getCustomerName());
            customerRepository.save(customer);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + customerId);
        });
    }

    /**
     * delete customer by id
     * @see CustomerServiceImpl#deleteById(UUID)
     * @param customerId - customer id
     */
    @Override
    public void deleteById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

}
