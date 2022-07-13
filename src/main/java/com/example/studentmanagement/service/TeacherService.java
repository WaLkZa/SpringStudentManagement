package com.example.studentmanagement.service;

import com.example.studentmanagement.model.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> getAll();

    Optional<Teacher> getById(Long id);

    Teacher create(Teacher newTeacher);

    void delete(Long id);
}