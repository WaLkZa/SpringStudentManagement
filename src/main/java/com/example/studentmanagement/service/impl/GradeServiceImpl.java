package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.exception.CourseNotFoundException;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.model.dto.GradeStudentCourseDto;
import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Grade;
import com.example.studentmanagement.model.entity.Student;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.GradeRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.GradeService;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Double getAverageInCourse(Long courseId) {
        this.courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return this.gradeRepository.findAverageGradeInCourse(courseId);
    }

    @Override
    public Double getAverageForStudent(Long studentId) {
        this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);

        return this.gradeRepository.findAverageGradeForStudent(studentId);
    }

    @Override
    public Grade addGradeForStudentInCourse(GradeStudentCourseDto newGrade) {
        Course findCourse = this.courseRepository.findById(newGrade.getCourseId()).orElseThrow(CourseNotFoundException::new);
        Student findStudent = this.studentRepository.findById(newGrade.getStudentId()).orElseThrow(StudentNotFoundException::new);

        return this.gradeRepository.save(
                new Grade(findStudent, findCourse, newGrade.getGrade()));
    }
}
