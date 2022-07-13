package com.example.studentmanagement.exception;

import com.example.studentmanagement.util.Constant;

public class StudentNotFoundException extends CustomRuntimeException {
    public StudentNotFoundException() {
        super(Constant.STUDENT_NOT_EXISTS);
    }
}
