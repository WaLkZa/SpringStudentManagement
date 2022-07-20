package com.example.studentmanagement.mapper.impl;

import com.example.studentmanagement.mapper.UserMapper;
import com.example.studentmanagement.model.dto.CreateUserRequest;
import com.example.studentmanagement.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserRequestToUser(CreateUserRequest createUserRequest) {
        User user = new User();

        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());

        return user;
    }
}

