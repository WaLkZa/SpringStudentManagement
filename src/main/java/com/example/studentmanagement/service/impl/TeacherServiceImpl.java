package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.TeacherNotFoundException;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.TeacherRepository;
import com.example.studentmanagement.service.TeacherService;
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
