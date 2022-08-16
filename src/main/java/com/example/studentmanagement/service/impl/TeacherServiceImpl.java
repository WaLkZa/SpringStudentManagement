package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.TeacherNotFoundException;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.TeacherRepository;
import com.example.studentmanagement.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService
{
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Page<Teacher> getAll(int page, int size, Sort.Direction order, String sortField) {
        Pageable pageable = PageRequest.of(page, size, order, sortField);

        return this.teacherRepository.findAll(pageable);
    }
    @Override
    public Page<Teacher> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.teacherRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> getAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return this.teacherRepository.findById(id);
    }

    @Override
    public Teacher create(Teacher newTeacher) {
        return this.teacherRepository.save(new Teacher(newTeacher.getName(), newTeacher.getDegree()));
    }

    @Override
    public void delete(Long id) {
        Teacher findTeacher = this.teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);

        this.teacherRepository.delete(findTeacher);
    }
}
