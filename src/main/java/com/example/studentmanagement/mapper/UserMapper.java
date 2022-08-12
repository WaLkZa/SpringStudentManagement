package com.example.studentmanagement.mapper;

import com.example.studentmanagement.model.dto.CreateUserRequest;
import com.example.studentmanagement.model.entity.User;

public interface UserMapper {

    User createUserRequestToUser(CreateUserRequest createUserRequest);
}
