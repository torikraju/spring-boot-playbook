package com.self.learning.playbook.impl;

import com.self.learning.playbook.entity.User;
import com.self.learning.playbook.repository.UserRepository;
import com.self.learning.playbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    private final UserRepository userRepository;

    @Override
    @Cacheable(key = "#email", value = "user", unless = "#result == null")
    public User findUserByEmail(String email) {
        LOGGER.info("finding-user-data-for:{}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
