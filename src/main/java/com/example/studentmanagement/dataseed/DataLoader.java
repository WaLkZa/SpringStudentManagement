package com.example.studentmanagement.dataseed;

import com.example.studentmanagement.service.RoleService;
import com.example.studentmanagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {
        this.roleService.seedRolesInDb();
        this.userService.seedFirstUserInDb();
    }
}
