package com.example.day4exercise.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, message = "name 2 letters or more")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "course name should contain 1 letter at least")
    @Column(columnDefinition = "VARCHAR(20) not null")
    private String name;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Teacher teacher;
}
