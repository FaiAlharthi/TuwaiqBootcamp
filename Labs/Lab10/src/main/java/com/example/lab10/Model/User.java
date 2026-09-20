package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4, message = "length of the name should be 4 or longer")
    @Pattern(regexp = "^[^0-9]*$", message = "name should contain only letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "email should be in a valid format")
    @Column(columnDefinition = " varchar(50) not null Unique")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @NotNull(message = "age cannot be empty")
    @Positive(message = "age should be more than 0")
    @Min(value = 21, message = "minimum age is 21")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$")
    @Column(columnDefinition = "varchar(12) not null")
    private String role;
}
