package com.example.day4exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "age cannot be empty")
    @Min(value = 21, message = "min age is 21")
    private Integer age;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email must be in a valid form")
    private String email;

    @NotNull(message = "age cannot be empty")
    @Min(value = 1000, message = "min salary is 1000")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;
}
