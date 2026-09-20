package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "you should add a title")
    @Size(min = 4, message = "title should be longer than 4 letters")
    @Column(columnDefinition = "varchar(50) Not null")
    private String title;

    @NotEmpty(message = "you should add a description")
    @Column(columnDefinition = "varchar(300) Not null")
    private String description;

    @NotEmpty(message = "you should add a location")
    @Column(columnDefinition = "varchar(25) Not null")
    private String location;

    @NotNull(message = "you should add a salary")
    @PositiveOrZero(message = "salary should be a positive number")
    @Column(columnDefinition = "int Not null")
    private Integer salary;

//    @NotNull(message = "you should add a salary")
    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", insertable = false)
    private LocalDateTime postingDate;
}
