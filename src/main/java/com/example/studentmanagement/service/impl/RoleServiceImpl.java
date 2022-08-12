package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.model.entity.Role;
import com.example.studentmanagement.repository.RoleRepository;
import com.example.studentmanagement.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_TEACHER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_STUDENT"));
        }
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(this.roleRepository.findAll());
    }
}
