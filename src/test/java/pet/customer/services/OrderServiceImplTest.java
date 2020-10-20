package pet.customer.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pet.customer.domain.Customer;
import pet.customer.domain.Order;
import pet.customer.repositories.CustomerRepository;
import pet.customer.repositories.OrderRepository;
import pet.customer.web.mappers.OrderMapper;
import pet.customer.web.model.OrderDto;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    OrderMapper orderMapper;

    @Test
    void listCustomerOrders() {
        UUID id = UUID.randomUUID();

        doReturn(Optional.of(Customer.builder().id(id).build()))
                .when(customerRepository)
                .findById(id);

        Set<Order> orders = Set.of(
                Order.builder().id(UUID.randomUUID()).description("desc").build(),
                Order.builder().id(UUID.randomUUID()).description("desc").build());
        doReturn(Customer.builder().id(id).orders(orders).build())
                .when(customerRepository)
                .findCustomerById(id);

        orderService.listCustomerOrders(id);
        verify(customerRepository, times(1))
                .findById(id);
        verify(customerRepository, times(1))
                .findCustomerById(id);
        verify(orderMapper, times(2))
                .orderToOrderDto(any());
    }

    @Test
    void placeOrder() {
        UUID id = UUID.randomUUID();
        OrderDto dto = OrderDto.builder().id(id).build();

        doReturn(Optional.of(Customer.builder().id(id).build()))
                .when(customerRepository)
                .findById(id);
        doReturn(new Order())
                .when(orderMapper)
                .orderDtoToOrder(dto);

        orderService.placeOrder(id, dto);

        verify(customerRepository, times(1))
                .findById(id);
        verify(orderMapper, times(1))
                .orderDtoToOrder(any());
        verify(orderRepository, times(1))
                .saveAndFlush(any());
        verify(orderMapper, times(1))
                .orderToOrderDto(any());

    }

    @Test
    void getOrderById() {
        UUID id = UUID.randomUUID();

        Optional<Order> order = Optional.of(Order.builder().id(id).build());

        doReturn(order)
                .when(orderRepository)
                .findById(id);

        orderService.getOrderById(id);

        verify(orderRepository, times(1))
                .findById(id);
        verify(orderMapper, times(1))
                .orderToOrderDto(any());
    }

    @Test
    void pickupOrder() {
        UUID id = UUID.randomUUID();

        Optional<Order> order = Optional.of(Order.builder().id(id).build());

        doReturn(order)
                .when(orderRepository)
                .findById(id);

        orderService.pickupOrder(id);

        verify(orderRepository, times(1))
                .findById(id);
        verify(orderRepository, times(1))
                .save(any());
    }
}