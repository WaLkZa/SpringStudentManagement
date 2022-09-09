package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.CourseNotFoundException;
import com.example.studentmanagement.exception.TeacherNotFoundException;
import com.example.studentmanagement.model.dto.CourseDto;
import com.example.studentmanagement.model.dto.CourseTeacherDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.TeacherRepository;
import com.example.studentmanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Page<CourseDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Course> courses = this.courseRepository.findAll(pageable);

        Page<CourseDto> mappedDto = courses.map(course -> this.modelMapper.map(course, CourseDto.class));

        return mappedDto;
    }

    @Override
    public Optional<Course> getById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public Course create(Course newCourse) {
        return this.courseRepository.save(new Course(newCourse.getName(), newCourse.getTotalHours()));
    }

    @Override
    public void delete(Long id) {
        Course findCourse = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        this.courseRepository.delete(findCourse);
    }

    @Override
    public Course addTeacherToCourse(CourseTeacherDto courseTeacherDto) {
        Course findCourse = this.courseRepository.findById(courseTeacherDto.getCourseId()).orElseThrow(CourseNotFoundException::new);
        Teacher findTeacher = this.teacherRepository.findById(courseTeacherDto.getTeacherId()).orElseThrow(TeacherNotFoundException::new);

        findCourse.setTeacher(findTeacher);

        return this.courseRepository.save(findCourse);
    }
}
