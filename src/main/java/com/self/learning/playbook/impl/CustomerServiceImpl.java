package com.self.learning.playbook.impl;

import com.self.learning.playbook.constants.Status;
import com.self.learning.playbook.dto.CustomerDto;
import com.self.learning.playbook.dto.CustomerResponse;
import com.self.learning.playbook.entity.Customer;
import com.self.learning.playbook.repository.CustomerRepository;
import com.self.learning.playbook.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    @Override
    @Cacheable(key = "#id",value = "customer")
    public CustomerResponse getCustomerResponseById(String id) {
        CustomerResponse response = new CustomerResponse();
        response.setApiRequestTime(new Date());
        try {
            Customer customer = getCustomerById(id);
            if (null == customer) throw new Exception("Customer not found");
            //CollectionUtils.isEmpty(customer);  //todo need in future
            response.setData(modelMapper.map(customer, CustomerDto.class));
            response.setStatus(Status.SUCCESSFUL);
        } catch (Exception e) {
            response.setStatus(Status.FAILED);
            response.setErrorDetails(e.getMessage());
//            LogUtils.logError(logger, "FAILED_TO_GET_ACTIVATION_DETAIL_BY_REQUEST_ID  | ERROR:" + e.getMessage());
        } finally {
            response.setApiResponseTime(new Date());
        }
        return response;
    }

    @Override
    public Customer getCustomerById(String id) {
        LOGGER.info("query-to-database-for-customer-id:{}", id);
        Customer customer = customerRepository.findById(id).orElse(null);
        LOGGER.info("query-to-database-found-customer:{}", customer);
        return customer;
    }


}
