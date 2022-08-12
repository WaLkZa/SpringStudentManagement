package com.example.studentmanagement.service;

import com.example.studentmanagement.model.entity.Role;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<Role> findAllRoles();
}
