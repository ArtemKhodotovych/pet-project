package pet.customer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.customer.domain.Customer;
import pet.customer.domain.Order;
import pet.customer.repositories.CustomerRepository;
import pet.customer.repositories.OrderRepository;
import pet.customer.web.mappers.OrderMapper;
import pet.customer.web.model.OrderDto;
import pet.customer.web.model.OrderStatusEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Order service implementation
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    /**
     * get all orders by customerId
     * @see OrderService#listCustomerOrders(UUID)
     * @param customerId - orders' customer id
     * @return found customer's orders
     * @throws RuntimeException
     */
    @Override
    public ResponseEntity<List> listCustomerOrders(UUID customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerRepository.findCustomerById(customerId);

            return new ResponseEntity<>(customer.getOrders()
                    .stream()
                    .map(orderMapper::orderToOrderDto)
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    /**
     * save new order for particular castomer
     * @see OrderServiceImpl#placeOrder(UUID, OrderDto)
     * @param customerId orders' customer id
     * @param orderDto - new order data
     * @return saved order
     * @throws RuntimeException
     */
    @Transactional
    @Override
    public OrderDto placeOrder(UUID customerId, OrderDto orderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Order order = orderMapper.orderDtoToOrder(orderDto);
            order.setId(null); //should not be set by outside client
            order.setCustomer(customerOptional.get());
            order.setOrderStatus(OrderStatusEnum.NEW);

            Order savedOrder = orderRepository.saveAndFlush(order);

            log.debug("Saved Order: " + order.getId());

            return orderMapper.orderToOrderDto(savedOrder);
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    /**
     * search order by id
     * @see OrderServiceImpl#getOrderById(UUID)
     * @param orderId - order's id
     * @return found order
     */
    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderMapper.orderToOrderDto(getOrder(orderId));
    }

    /**
     * get order from db
     * @see OrderServiceImpl#getOrder(UUID)
     * @param orderId - order id
     * @return found db order
     * @throws RuntimeException
     */
    private Order getOrder(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        throw new RuntimeException("Order Not Found");
    }

    /**
     * set order status to picked up
     * @see OrderServiceImpl#pickupOrder(UUID)
     * @param orderId - order id
     */
    @Override
    public void pickupOrder(UUID orderId) {
        Order order = getOrder(orderId);
        order.setOrderStatus(OrderStatusEnum.PICKED_UP);
        orderRepository.save(order);
    }

}
