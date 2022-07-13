package com.example.studentmanagement.exception;

import com.example.studentmanagement.util.Constant;

public class TeacherNotFoundException extends CustomRuntimeException {
    public TeacherNotFoundException() {
        super(Constant.TEACHER_NOT_EXISTS);
    }
}
