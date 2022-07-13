package com.example.studentmanagement.service;


import com.example.studentmanagement.model.dto.GradeStudentCourseDto;
import com.example.studentmanagement.model.entity.Grade;

public interface GradeService {

    Double getAverageInCourse(Long courseId);

    Double getAverageForStudent(Long studentId);

    Grade addGradeForStudentInCourse(GradeStudentCourseDto newGrade);
}
