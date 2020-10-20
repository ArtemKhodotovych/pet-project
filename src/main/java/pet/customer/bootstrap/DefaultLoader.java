package pet.customer.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pet.customer.domain.Customer;
import pet.customer.domain.Order;
import pet.customer.repositories.CustomerRepository;
import pet.customer.repositories.OrderRepository;
import pet.customer.web.model.OrderStatusEnum;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class DefaultLoader implements CommandLineRunner {

    /**
     * {@value DefaultLoader#USUAL_CUSTOMER_NAME
     * {@value DefaultLoader#ANOTHER_USUAL_CUSTOMER_NAME
     */
    public static final String USUAL_CUSTOMER_NAME = "Usual Customer Name";
    public static final String ANOTHER_USUAL_CUSTOMER_NAME = "Another Usual Customer Name";

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    /**
     * method start after context initialization
     * @param args unnecessary command line params
     * @see DefaultLoader#run(String...)
     */
    @Override
    public void run(String... args) {
        loadCustomersData();
    }

    /**
     * method fill Db with testing data
     * @see DefaultLoader#loadCustomersData()
     */
    private void loadCustomersData() {

        Customer usualCustomer = Customer.builder()
                .customerName(USUAL_CUSTOMER_NAME)
                .id(UUID.fromString("97df0c39-90c4-4ae0-b663-453e8e19c311"))
                .build();

        Customer anotherUsualCustomer = Customer.builder()
                .customerName(ANOTHER_USUAL_CUSTOMER_NAME)
                .id(UUID.fromString("432823e9-ca5e-4a09-b44a-2b2606d02341"))
                .build();

        customerRepository.saveAll(Arrays.asList(usualCustomer, anotherUsualCustomer));

        Order order = Order.builder()
                .orderStatus(OrderStatusEnum.NEW)
                .description("Want to buy new phone")
                .customer(usualCustomer)
                .build();

        Order order2 = Order.builder()
                .orderStatus(OrderStatusEnum.NEW)
                .description("New TV-set")
                .customer(usualCustomer)
                .build();

        Order order3 = Order.builder()
                .orderStatus(OrderStatusEnum.NEW)
                .description("New car")
                .customer(anotherUsualCustomer)
                .build();

        Order order4 = Order.builder()
                .orderStatus(OrderStatusEnum.NEW)
                .description("New apartment")
                .customer(anotherUsualCustomer)
                .build();

        orderRepository.saveAll(Arrays.asList(order, order2, order3, order4));
    }

}
