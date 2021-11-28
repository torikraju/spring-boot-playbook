package com.self.learning.playbook;

import com.self.learning.playbook.entity.Customer;
import com.self.learning.playbook.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCustomerFindAll() {
        List<Customer> allCustomer = customerRepository.findAll();
        LOGGER.info("all customer count: {}", allCustomer.size());
    }

}
