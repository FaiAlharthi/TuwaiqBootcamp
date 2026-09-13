package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "name should be only letters with no chars and special chars")
    @Size(min = 3, message = "length of the name should be more than 2 letters")
    private String name;

    @NotEmpty(message = "Student ID cannot be empty")
    @Pattern(regexp = "^4\\d{8}$", message = "Student ID must be 9 digits and start with 4")
    private String studentID;

    @Size(min = 1, message = "Student must study 1 course at least")
    private ArrayList<Course> studentCourses;

    @Max(value = 5, message = "GPA highest score is 5")
    private double GPA;

    public void addCourse (Course course){
        studentCourses.add(course);
    }
}
