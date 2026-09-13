package com.example.exam2.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotEmpty(message= "Id should not be empty")
    @Size(min = 2, message = "ID number is more than 2 chars")
    private String ID;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, message = "name should be more than 2 letters")
    private String name;

    @NotNull(message = "Salary should not be empty")
    @Min(value = 1000, message = "Salary should be more than 1000")
    private double salary;
}
