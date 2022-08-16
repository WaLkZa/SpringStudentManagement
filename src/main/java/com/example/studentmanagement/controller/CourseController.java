package com.example.studentmanagement.controller;


import com.example.studentmanagement.model.dto.CourseDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.dto.CourseTeacherDto;
import com.example.studentmanagement.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courses = this.courseService.getAll();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Page<CourseDto>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(this.courseService.getAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Course> getById(@PathVariable("id") long id) {
        Optional<Course> courseData = this.courseService.getById(id);

        return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
    }

    @PostMapping("/add-teacher")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> addTeacherToCourse(@RequestBody CourseTeacherDto courseTeacherDto) {
        return new ResponseEntity<>(this.courseService.addTeacherToCourse(courseTeacherDto), HttpStatus.CREATED);
    }

    @PostMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Course> create(@RequestBody Course newCourse) {
        return new ResponseEntity<>(this.courseService.create(newCourse), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        this.courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
