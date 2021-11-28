package com.self.learning.playbook.specification;

import com.self.learning.playbook.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public interface CustomerSpecification {

    static Specification<Customer> findById(String id) {
        return (user, cq, cb) -> cb.equal(user.get("id"), id);
    }

}
