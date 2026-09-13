package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "name should be only letters with no chars and special chars")
    @Size(min = 3, message = "length of the name should be more than 2 letters")
    private String name;

    @NotEmpty(message = "course code cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]{2,4}\\d{3}$", message = "course Code should starts with 2 to 4 letters then 3 digits")
    private String code;

    @NotEmpty(message = "Instructor name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Instructor name should be only letters with no chars and special chars")
    private String instructorName;

    private ArrayList<@Size(min = 5, message = "5 letters at least is required for each announcement") String> announcements;

    @Min(value = 2, message = "each course should have 1 exam at least besides tha final exam")
    private int numberOfRequiredExams;

    @Min(value= 0, message = "highest grade is 100")
    @Max(value = 100, message = "highest grade is 100")
    private double finalGrade;

    public void addAnnouncement (String announcement){
        announcements.add(announcement);
    }


}
