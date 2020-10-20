package pet.customer.web.mappers;

import org.mapstruct.Mapper;
import pet.customer.domain.Customer;
import pet.customer.web.model.CustomerDto;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * generate default Customer mapper
 */
@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto dto);

}
