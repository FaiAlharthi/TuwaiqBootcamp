package com.example.day4exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, message = "name 2 letters or more")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "course name should contain 1 letter at least")
    @Column(columnDefinition = "VARCHAR(20) not null")
    private String name;

    @NotEmpty(message = "major cannot be empty")
    @Size(min = 2, message = "major 2 letters or more")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "major contain letters only")
    @Column(columnDefinition = "VARCHAR(20) not null")
    private String major;

    @NotEmpty(message = "age cannot be empty")
    @Min(value = 19, message = "min age is 19 years old")
    @Column(columnDefinition = "Integer not null")
    private Integer age;

    @ManyToMany
    private Set<Course> courses;

}
