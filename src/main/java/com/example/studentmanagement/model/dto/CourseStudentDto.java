package com.example.studentmanagement.model.dto;

public class CourseStudentDto {

    private Long courseId;
    private Long studentId;

    public CourseStudentDto() {
    }

    public CourseStudentDto(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
