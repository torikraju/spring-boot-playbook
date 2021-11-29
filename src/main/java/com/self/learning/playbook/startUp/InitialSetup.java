package com.self.learning.playbook.startUp;

import com.self.learning.playbook.entity.Role;
import com.self.learning.playbook.entity.User;
import com.self.learning.playbook.repository.UserRepository;
import com.self.learning.playbook.service.RoleService;
import com.self.learning.playbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialSetup implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private void setUpRoles() {
        List<Role> roleList = roleService.findAll();
        if (roleList.size() == 0) {
            List<Role> roles = new ArrayList<>();
            Role userRole = new Role("ROLE_USER");
            Role userAdmin = new Role("ROLE_ADMIN");
            roles.add(userRole);
            roles.add(userAdmin);
            roleService.saveAll(roles);
        }
    }

    private void addUser() {
        if (userService.countUsers() == 0) {
            User user = new User();
            user.setFirstName("torikul");
            user.setLastName("alam");
            user.setEmail("torikraju@gmail.com");
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            user.setRoles(new ArrayList<Role>() {{
                add(roleService.findByName("ROLE_ADMIN"));
            }});
            userService.save(user);
        }

    }

    @Override
    public void run(ApplicationArguments args) {
        setUpRoles();
        addUser();
    }
}
