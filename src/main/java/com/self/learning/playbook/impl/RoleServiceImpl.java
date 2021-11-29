package com.self.learning.playbook.impl;

import com.self.learning.playbook.entity.Role;
import com.self.learning.playbook.repository.RoleRepository;
import com.self.learning.playbook.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
