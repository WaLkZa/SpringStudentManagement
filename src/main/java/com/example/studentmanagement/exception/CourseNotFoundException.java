package com.example.studentmanagement.exception;

import com.example.studentmanagement.util.Constant;

public class CourseNotFoundException extends CustomRuntimeException{
    public CourseNotFoundException() {
        super(Constant.COURSE_NOT_EXISTS);
    }
}