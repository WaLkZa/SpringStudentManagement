package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.entity.Grade;
import com.example.studentmanagement.model.dto.GradeStudentCourseDto;
import com.example.studentmanagement.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/average-in-course/{courseId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Double> getAverageInCourse(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(this.gradeService.getAverageInCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/average-for-student/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Double> getAverageForStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(this.gradeService.getAverageForStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/add-grade")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ResponseEntity<Grade> addGradeForStudentInCourse(@RequestBody GradeStudentCourseDto newGrade) {
        return new ResponseEntity<>(this.gradeService.addGradeForStudentInCourse(newGrade), HttpStatus.CREATED);
    }
}
