package com.self.learning.playbook.service;

import com.self.learning.playbook.entity.User;

public interface UserService {
    User findUserByEmail(String Email);

    User findUserById(Long userId);

    long countUsers();

    void save(User user);
}
