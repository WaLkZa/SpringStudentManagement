package com.example.studentmanagement.service;

import com.example.studentmanagement.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Page<Teacher> getAll(int page, int size, Sort.Direction order, String sortField);

    Page<Teacher> getAll(int page, int size);

    List<Teacher> getAll();

    Optional<Teacher> getById(Long id);

    Teacher create(Teacher newTeacher);

    void delete(Long id);
}