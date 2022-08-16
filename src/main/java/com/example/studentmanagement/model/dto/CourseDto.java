package com.example.studentmanagement.model.dto;

public class CourseDto {

    private Long id;
    private String name;
    private int totalHours;

    public CourseDto() {
    }

    public CourseDto(Long id, String name, int totalHours) {
        this.id = id;
        this.name = name;
        this.totalHours = totalHours;
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

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
