package com.example.lab6.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @NotEmpty(message = "ID Cannot be empty !")
    @Size(min = 3, message = "ID Cannot be shorter than 3 characters")
    private String ID;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, message = "name should be longer than 4 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "name should contain only Letters (NO numbers or Special Characters")
    private String name;

    @Email(message = "please write a valid email")
    private String email;

//    @Size(min=10, max=10, message = "Phone number should be only 10 Digits !")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number should be: All Digits, with length of 10, and starts with 05  !")
    private String phoneNumber;

    @NotNull(message = "age cannot be empty")
    @Positive(message = "age should be positive numbers only")
    @Min(value = 26, message = "age should be more than 25")
    private int age;

    @NotEmpty(message = "position cannot be empty")
    @Pattern(regexp = "^(supervisor|coordinator)$", message = "position only should be ( Supervisor OR Coordinator )")
    private String position;

    @AssertFalse(message = "The employee should still be active")
    private boolean onLeave;

    @NotNull(message = "you should enter the hire date")
    @PastOrPresent(message = "The date of hiring should be in the past if not today")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate hireDate;

    @NotNull(message = "annual leave cannot be empty")
    @PositiveOrZero(message = "annual leave should be a positive number")
    private int annualLeave;

}
