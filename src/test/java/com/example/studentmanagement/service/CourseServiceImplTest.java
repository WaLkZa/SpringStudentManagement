package com.example.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.exception.CourseNotFoundException;
import com.example.studentmanagement.model.dto.CourseTeacherDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.TeacherRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.example.studentmanagement.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CourseServiceImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void testGetAll() {
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> actualAll = courseServiceImpl.getAll();
        assertSame(courseList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetAll2() {
        when(courseRepository.findAll()).thenThrow(new CourseNotFoundException());
        assertThrows(CourseNotFoundException.class, () -> courseServiceImpl.getAll());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetById() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");

        Course course = new Course();
        course.setId(123L);
        course.setName("Name");
        course.setStudents(new HashSet<>());
        course.setTeacher(teacher);
        course.setTotalHours(1);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Course> actualById = courseServiceImpl.getById(123L);
        assertSame(ofResult, actualById);
        assertTrue(actualById.isPresent());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    void testCreate() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");

        Course course = new Course();
        course.setId(123L);
        course.setName("Name");
        course.setStudents(new HashSet<>());
        course.setTeacher(teacher);
        course.setTotalHours(1);
        when(courseRepository.save((Course) any())).thenReturn(course);

        Teacher teacher1 = new Teacher();
        teacher1.setDegree("Degree");
        teacher1.setId(123L);
        teacher1.setName("Name");

        Course course1 = new Course();
        course1.setId(123L);
        course1.setName("Name");
        course1.setStudents(new HashSet<>());
        course1.setTeacher(teacher1);
        course1.setTotalHours(1);
        assertSame(course, courseServiceImpl.create(course1));
        verify(courseRepository).save((Course) any());
    }

    @Test
    void testAddTeacherToCourse() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");

        Course course = new Course();
        course.setId(123L);
        course.setName("Name");
        course.setStudents(new HashSet<>());
        course.setTeacher(teacher);
        course.setTotalHours(1);
        Optional<Course> ofResult = Optional.of(course);

        Teacher teacher1 = new Teacher();
        teacher1.setDegree("Degree");
        teacher1.setId(123L);
        teacher1.setName("Name");

        Course course1 = new Course();
        course1.setId(123L);
        course1.setName("Name");
        course1.setStudents(new HashSet<>());
        course1.setTeacher(teacher1);
        course1.setTotalHours(1);
        when(courseRepository.save((Course) any())).thenReturn(course1);
        when(courseRepository.findById((Long) any())).thenReturn(ofResult);

        Teacher teacher2 = new Teacher();
        teacher2.setDegree("Degree");
        teacher2.setId(123L);
        teacher2.setName("Name");
        Optional<Teacher> ofResult1 = Optional.of(teacher2);
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult1);
        assertSame(course1, courseServiceImpl.addTeacherToCourse(new CourseTeacherDto()));
        verify(courseRepository).save((Course) any());
        verify(courseRepository).findById((Long) any());
        verify(teacherRepository).findById((Long) any());
    }

    @Test
    void testAddTeacherToCourse2() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");

        Course course = new Course();
        course.setId(123L);
        course.setName("Name");
        course.setStudents(new HashSet<>());
        course.setTeacher(teacher);
        course.setTotalHours(1);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save((Course) any())).thenThrow(new CourseNotFoundException());
        when(courseRepository.findById((Long) any())).thenReturn(ofResult);

        Teacher teacher1 = new Teacher();
        teacher1.setDegree("Degree");
        teacher1.setId(123L);
        teacher1.setName("Name");
        Optional<Teacher> ofResult1 = Optional.of(teacher1);
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(CourseNotFoundException.class, () -> courseServiceImpl.addTeacherToCourse(new CourseTeacherDto()));
        verify(courseRepository).save((Course) any());
        verify(courseRepository).findById((Long) any());
        verify(teacherRepository).findById((Long) any());
    }
}