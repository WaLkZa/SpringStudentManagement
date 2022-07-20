package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.entity.Student;
import com.example.studentmanagement.model.dto.CourseStudentDto;
import com.example.studentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(this.studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Student> getById(@PathVariable("id") long id) {
        Optional<Student> studentData = this.studentService.getById(id);

        return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Student> create(@RequestBody Student newStudent) {
        return new ResponseEntity<>(this.studentService.create(newStudent), HttpStatus.CREATED);
    }

    @PostMapping("/add-course")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Student> addStudentToCourse(@RequestBody CourseStudentDto courseStudentDto) {
        return new ResponseEntity<>(this.studentService.addStudentToCourse(courseStudentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        this.studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
