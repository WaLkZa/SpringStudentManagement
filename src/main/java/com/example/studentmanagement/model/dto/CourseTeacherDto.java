package com.example.studentmanagement.model.dto;

public class CourseTeacherDto {

    private Long courseId;
    private Long teacherId;

    public CourseTeacherDto() {
    }

    public CourseTeacherDto(Long courseId, Long teacherId) {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
