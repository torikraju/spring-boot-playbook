package com.self.learning.playbook.service;

import com.self.learning.playbook.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    void saveAll(List<Role> roles);
    Role findByName(String name);
}
