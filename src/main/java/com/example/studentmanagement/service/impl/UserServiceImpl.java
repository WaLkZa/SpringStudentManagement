package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.UserNotFoundException;
import com.example.studentmanagement.mapper.UserMapper;
import com.example.studentmanagement.model.dto.CreateUserRequest;
import com.example.studentmanagement.model.entity.Role;
import com.example.studentmanagement.model.entity.User;
import com.example.studentmanagement.repository.RoleRepository;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.service.RoleService;
import com.example.studentmanagement.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final RoleService roleService;
    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(CreateUserRequest createUserRequest) {

        User userEntity = userMapper.createUserRequestToUser(createUserRequest);

        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAuthorities(this.getAuthorities("ROLE_STUDENT"));

        return this.userRepository.save(userEntity);
    }

    public void seedFirstUserInDb() {
        if (this.userRepository.count() == 0) {
            User firstUser = new User();
            firstUser.setUsername("Owner");
            firstUser.setPassword(this.bCryptPasswordEncoder.encode("Owner"));
            firstUser.setAuthorities(this.roleService.findAllRoles());
            this.userRepository.save(firstUser);
        }
    }

    private Set<Role> getAuthorities(String authority) {
        Set<Role> userAuthorities = new LinkedHashSet<>();

        userAuthorities.add(this.roleRepository.getByAuthority(authority));

        return userAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
