package com.example.exam2.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Id should not be empty")
    @Size(min = 2, message = "ID should be more than 2 chars")
    private String ID;

    @NotEmpty(message ="name should not be empty")
    @Size(min = 3, message = "name should be more than 2 letters")
    private String name;

    @NotNull(message = "age should not be empty")
    @Min(value = 18, message = "age should be more than 18")
    private int age;

    @NotEmpty(message = "major should not be empty")
    private String major;

}
