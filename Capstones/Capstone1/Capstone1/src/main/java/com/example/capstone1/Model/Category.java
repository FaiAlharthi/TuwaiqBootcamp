package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4, message = "category name should be more than 3 letters")
    private String name;
}
