package pet.customer.web.controllers.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.customer.services.CustomerService;
import pet.customer.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Customer Rest API Controller
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    /**
     * get all customers
     * @see CustomerRestController#getCustomers()
     * @return all found customers
     */
    @GetMapping(path = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getCustomers(){
        log.debug("Listing Customers");

        List<CustomerDto> customers = customerService.listCustomers();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * get customer by id
     * @see CustomerRestController#getCustomerById(UUID)
     * @param customerId - particular customer id
     * @return found customer
     */
    @GetMapping(path = {"customer/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId){
        log.debug("Get Request for CustomerId: " + customerId);

        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    /**
     * save new customer
     * @see CustomerRestController#saveNewCustomer(CustomerDto)
     * @param customerDto - new customer
     * @return saved customer
     */
    @PostMapping(path = "customer")
    public ResponseEntity<CustomerDto> saveNewCustomer(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto savedDto = customerService.saveCustomer(customerDto);

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    /**
     * update existing customer
     * @see CustomerRestController#updateCustomer(UUID, CustomerDto)
     * @param customerId - particular customer id
     * @param customerDto - updatable customer dto
     * @return 204
     */
    @PutMapping(path = {"customer/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @see CustomerRestController#deleteCustomer(UUID)
     * @param customerId - particular customer id
     * @return 204
     */
    @DeleteMapping({"customer/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);
    }

    /**
     * controller exception handler
     * @param e - cached exception
     * @return 400
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List> badRequestHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
