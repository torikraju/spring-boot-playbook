package com.self.learning.playbook.resource;

import com.self.learning.playbook.annotation.ApiController;
import com.self.learning.playbook.assembler.CustomerModelAssembler;
import com.self.learning.playbook.dto.response.CustomerAllResponse;
import com.self.learning.playbook.dto.CustomerDto;
import com.self.learning.playbook.dto.response.CustomerResponse;
import com.self.learning.playbook.entity.Customer;
import com.self.learning.playbook.event.CustomerRegistrationEvent;
import com.self.learning.playbook.impl.CustomerServiceImpl;
import com.self.learning.playbook.repository.CustomerRepository;
import com.self.learning.playbook.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@ApiController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class);


    private final CustomerRepository customerRepository;
    private final CustomerModelAssembler customerModelAssembler;
    private final PagedResourcesAssembler<Customer> pagedResourcesAssembler;
    private final ApplicationEventPublisher publisher;

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Customer> allCustomer = customerRepository.findAll();
        return new ResponseEntity<>(customerModelAssembler.toCollectionModel(allCustomer), HttpStatus.OK);
    }


    @GetMapping("/all-customer-list")
    public ResponseEntity<CustomerAllResponse> getAllAlbums(Pageable pageable) {
        LOGGER.info("fetching-all-customer");
        Page<Customer> allCustomer = customerRepository.findAll(pageable);
        PagedModel<CustomerDto> collModel = pagedResourcesAssembler.toModel(allCustomer, customerModelAssembler);
        CustomerAllResponse response = new CustomerAllResponse();
        response.setData(collModel);
        publisher.publishEvent(new CustomerRegistrationEvent("test event"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomerResponseById(id), HttpStatus.OK);
    }


}
