package com.example.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.model.dto.CourseStudentDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Student;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.example.studentmanagement.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    void testGetAll() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualAll = studentServiceImpl.getAll();
        assertSame(studentList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(studentRepository).findAll();
    }

    @Test
    void testGetAll2() {
        when(studentRepository.findAll()).thenThrow(new StudentNotFoundException());
        assertThrows(StudentNotFoundException.class, () -> studentServiceImpl.getAll());
        verify(studentRepository).findAll();
    }

    @Test
    void testGetById() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Student> actualById = studentServiceImpl.getById(123L);
        assertSame(ofResult, actualById);
        assertTrue(actualById.isPresent());
        verify(studentRepository).findById((Long) any());
    }

    @Test
    void testGetById2() {
        when(studentRepository.findById((Long) any())).thenThrow(new StudentNotFoundException());
        assertThrows(StudentNotFoundException.class, () -> studentServiceImpl.getById(123L));
        verify(studentRepository).findById((Long) any());
    }

    @Test
    void testCreate() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setCourses(new HashSet<>());
        student1.setGrades(new ArrayList<>());
        student1.setId(123L);
        student1.setName("Name");
        assertSame(student, studentServiceImpl.create(student1));
        verify(studentRepository).save((Student) any());
    }

    @Test
    void testCreate2() {
        when(studentRepository.save((Student) any())).thenThrow(new StudentNotFoundException());

        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        assertThrows(StudentNotFoundException.class, () -> studentServiceImpl.create(student));
        verify(studentRepository).save((Student) any());
    }

    @Test
    void testDelete() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        doNothing().when(studentRepository).deleteById((Long) any());
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        studentServiceImpl.delete(123L);
        verify(studentRepository).findById((Long) any());
        verify(studentRepository).deleteById((Long) any());
    }

    @Test
    void testDelete2() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        doThrow(new StudentNotFoundException()).when(studentRepository).deleteById((Long) any());
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(StudentNotFoundException.class, () -> studentServiceImpl.delete(123L));
        verify(studentRepository).findById((Long) any());
        verify(studentRepository).deleteById((Long) any());
    }

    @Test
    void testAddStudentToCourse() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setCourses(new HashSet<>());
        student1.setGrades(new ArrayList<>());
        student1.setId(123L);
        student1.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student1);
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
        when(courseRepository.findById((Long) any())).thenReturn(ofResult1);
        assertSame(student, studentServiceImpl.addStudentToCourse(new CourseStudentDto()));
        verify(studentRepository).save((Student) any());
        verify(studentRepository).findById((Long) any());
        verify(courseRepository).save((Course) any());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    void testAddStudentToCourse2() {
        Student student = new Student();
        student.setAge(1);
        student.setCourses(new HashSet<>());
        student.setGrades(new ArrayList<>());
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setCourses(new HashSet<>());
        student1.setGrades(new ArrayList<>());
        student1.setId(123L);
        student1.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student1);
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
        when(courseRepository.save((Course) any())).thenThrow(new StudentNotFoundException());
        when(courseRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(StudentNotFoundException.class, () -> studentServiceImpl.addStudentToCourse(new CourseStudentDto()));
        verify(studentRepository).save((Student) any());
        verify(studentRepository).findById((Long) any());
        verify(courseRepository).save((Course) any());
        verify(courseRepository).findById((Long) any());
    }
}

