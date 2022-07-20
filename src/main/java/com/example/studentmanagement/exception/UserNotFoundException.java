package com.example.studentmanagement.exception;

import com.example.studentmanagement.util.Constant;

public class UserNotFoundException extends CustomRuntimeException {
    public UserNotFoundException() {
        super(Constant.USER_NOT_EXISTS);
    }
}
