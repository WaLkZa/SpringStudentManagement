package com.example.studentmanagement.model.dto;

public class GradeStudentCourseDto extends CourseStudentDto {

    private double grade;

    public GradeStudentCourseDto() {
    }

    public GradeStudentCourseDto(Long courseId, Long studentId, double grade) {
        super(courseId, studentId);
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
