package com.example.studentmanagement.service;

import com.example.studentmanagement.model.dto.CourseTeacherDto;
import com.example.studentmanagement.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAll();

    Optional<Course> getById(Long id);

    Course create(Course newCourse);

    void delete(Long id);

    Course addTeacherToCourse(CourseTeacherDto courseTeacherDto);
}
