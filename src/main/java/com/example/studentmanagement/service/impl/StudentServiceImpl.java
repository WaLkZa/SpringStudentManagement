package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.CourseNotFoundException;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.model.dto.CourseStudentDto;
import com.example.studentmanagement.model.dto.StudentDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Student;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = this.studentRepository.findAll();

        List<StudentDto> mappedDto = students.stream()
                .map(student -> this.modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());

        return mappedDto;
    }

    @Override
    public Page<StudentDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Student> students = this.studentRepository.findAll(pageable);

        Page<StudentDto> mappedDto = students.map(student -> this.modelMapper.map(student, StudentDto.class));

        return mappedDto;
    }

    @Override
    public Optional<Student> getById(Long id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Student create(Student newStudent) {
        return this.studentRepository.save(new Student(newStudent.getName(), newStudent.getAge()));
    }

    @Override
    public void delete(Long id) {
        this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        this.studentRepository.deleteById(id);
    }

    @Override
    public Student addStudentToCourse(CourseStudentDto courseStudentDto) {
        Course findCourse = this.courseRepository.findById(courseStudentDto.getCourseId()).orElseThrow(CourseNotFoundException::new);
        Student findStudent = this.studentRepository.findById(courseStudentDto.getStudentId()).orElseThrow(StudentNotFoundException::new);

        findStudent.getCourses().add(findCourse);
        findCourse.getStudents().add(findStudent);

        this.studentRepository.save(findStudent);
        this.courseRepository.save(findCourse);

        return findStudent;
    }
}