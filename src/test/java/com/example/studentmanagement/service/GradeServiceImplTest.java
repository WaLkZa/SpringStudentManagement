package com.example.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.exception.CourseNotFoundException;
import com.example.studentmanagement.model.dto.GradeStudentCourseDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Grade;
import com.example.studentmanagement.model.entity.Student;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.GradeRepository;
import com.example.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;


import com.example.studentmanagement.service.impl.GradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GradeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GradeServiceImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private GradeRepository gradeRepository;

    @Autowired
    private GradeServiceImpl gradeServiceImpl;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void testGetAverageInCourse() {
        when(gradeRepository.findAverageGradeInCourse(anyLong())).thenReturn(10.0d);

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
        assertEquals(10.0d, gradeServiceImpl.getAverageInCourse(123L).doubleValue());
        verify(gradeRepository).findAverageGradeInCourse(anyLong());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    void testGetAverageInCourse2() {
        when(gradeRepository.findAverageGradeInCourse(anyLong())).thenThrow(new CourseNotFoundException());

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
        assertThrows(CourseNotFoundException.class, () -> gradeServiceImpl.getAverageInCourse(123L));
        verify(gradeRepository).findAverageGradeInCourse(anyLong());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    void testGetAverageForStudent() {
        when(gradeRepository.findAverageGradeForStudent((Long) any())).thenReturn(10.0d);

        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals(10.0d, gradeServiceImpl.getAverageForStudent(123L).doubleValue());
        verify(gradeRepository).findAverageGradeForStudent((Long) any());
        verify(studentRepository).findById((Long) any());
    }

    @Test
    void testGetAverageForStudent2() {
        when(gradeRepository.findAverageGradeForStudent((Long) any())).thenThrow(new CourseNotFoundException());

        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(CourseNotFoundException.class, () -> gradeServiceImpl.getAverageForStudent(123L));
        verify(gradeRepository).findAverageGradeForStudent((Long) any());
        verify(studentRepository).findById((Long) any());
    }

    @Test
    void testAddGradeForStudentInCourse() {
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

        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");

        Grade grade = new Grade();
        grade.setCourse(course);
        grade.setGrade(10.0d);
        grade.setId(123L);
        grade.setStudent(student);
        when(gradeRepository.save((Grade) any())).thenReturn(grade);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setCourses(new HashSet<>());
        student1.setGrades(new ArrayList<>());
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult = Optional.of(student1);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);

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
        Optional<Course> ofResult1 = Optional.of(course1);
        when(courseRepository.findById((Long) any())).thenReturn(ofResult1);
        assertSame(grade, gradeServiceImpl.addGradeForStudentInCourse(new GradeStudentCourseDto()));
        verify(gradeRepository).save((Grade) any());
        verify(studentRepository).findById((Long) any());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    void testAddGradeForStudentInCourse2() {
        when(gradeRepository.save((Grade) any())).thenThrow(new CourseNotFoundException());

        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);

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
        Optional<Course> ofResult1 = Optional.of(course);
        when(courseRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(CourseNotFoundException.class,
                () -> gradeServiceImpl.addGradeForStudentInCourse(new GradeStudentCourseDto()));
        verify(gradeRepository).save((Grade) any());
        verify(studentRepository).findById((Long) any());
        verify(courseRepository).findById((Long) any());
    }
}

