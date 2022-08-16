package com.example.studentmanagement.service;

import com.example.studentmanagement.model.dto.CourseStudentDto;
import com.example.studentmanagement.model.dto.StudentDto;
import com.example.studentmanagement.model.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> getAll();

    Page<StudentDto> getAll(int page, int size);

    Optional<Student> getById(Long id);

    Student create(Student newStudent);

    void delete(Long id);

    Student addStudentToCourse(CourseStudentDto courseStudentDto);
}
