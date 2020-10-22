package pet.customer.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pet.customer.domain.Customer;
import pet.customer.repositories.CustomerRepository;
import pet.customer.web.controllers.api.BaseTest;
import pet.customer.web.mappers.CustomerMapper;
import pet.customer.web.model.CustomerDto;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceImplTest extends BaseTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerMapper customerMapper;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void listCustomers() {
        doReturn(Arrays.asList(new Customer(), new Customer()))
                .when(customerRepository)
                .findAll();

        customerService.listCustomers();
        Mockito.verify(customerRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(customerMapper, Mockito.times(2))
                .customerToCustomerDto(any());
    }

    @Test
    void findCustomerById() {
        UUID id = UUID.fromString("998ad699-de8c-42ae-b39c-da99108cc90e");

        doReturn(Optional.of(new Customer()))
            .when(customerRepository)
            .findById(id);

        customerService.findCustomerById(id);
        Mockito.verify(customerRepository, Mockito.times(1))
                .findById(id);
    }

    @Test
    void saveCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .customerName("Customer Name")
                .version(2).build();

        doReturn(new Customer())
                .when(customerRepository)
                .save(any());

        customerService.saveCustomer(customerDto);
        Mockito.verify(customerMapper, Mockito.times(1)).customerDtoToCustomer(customerDto);
        Mockito.verify(customerRepository, Mockito.times(1))
                .save(any());
        Mockito.verify(customerMapper, Mockito.times(1))
                .customerToCustomerDto(any());
    }

    @Test
    void updateCustomer() {
        UUID id = UUID.randomUUID();
        CustomerDto dto = CustomerDto.builder().id(id).customerName("new name").build();

        doReturn(Optional.of(new Customer()))
                .when(customerRepository)
                .findById(id);

        customerService.updateCustomer(id, dto);
        verify(customerRepository, Mockito.times(1))
                .findById(id);
        verify(customerRepository, Mockito.times(1))
                .save(any());
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();

        customerService.deleteById(id);
        verify(customerRepository, times(1))
                .deleteById(id);

    }
}