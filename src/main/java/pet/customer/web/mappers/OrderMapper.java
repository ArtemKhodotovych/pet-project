package pet.customer.web.mappers;

import org.mapstruct.Mapper;
import pet.customer.domain.Order;
import pet.customer.web.model.OrderDto;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * generate default Order mapper
 */
@Mapper(uses = {DateMapper.class})
public interface OrderMapper {

    OrderDto orderToOrderDto(Order customer);

    Order orderDtoToOrder(OrderDto dto);

}
