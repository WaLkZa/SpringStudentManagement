package com.example.studentmanagement.service;

import com.example.studentmanagement.model.dto.CreateUserRequest;
import com.example.studentmanagement.model.entity.User;

public interface UserService {

    void seedFirstUserInDb();

    User createUser(CreateUserRequest userRequest);
}
