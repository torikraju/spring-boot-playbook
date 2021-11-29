package com.self.learning.playbook.service;

import com.self.learning.playbook.dto.response.CustomerResponse;
import com.self.learning.playbook.entity.Customer;

public interface CustomerService {
    CustomerResponse getCustomerResponseById(String id);
    Customer getCustomerById(String id);
}
