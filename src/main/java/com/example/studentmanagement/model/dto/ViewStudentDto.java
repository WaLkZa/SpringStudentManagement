package com.example.studentmanagement.model.dto;

import com.example.studentmanagement.model.entity.Course;
import com.example.studentmanagement.model.entity.Grade;

import java.util.List;
import java.util.Set;

public class ViewStudentDto {
    private Long id;

    private String name;

    private int age;

    private Set<Course> courses;

    private List<Grade> grades;

    public ViewStudentDto() {
    }

    public ViewStudentDto(Long id, String name, int age, Set<Course> courses, List<Grade> grades) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = courses;
        this.grades = grades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
