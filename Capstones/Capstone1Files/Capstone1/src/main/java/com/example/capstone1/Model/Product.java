package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4)
    private String name;

    @NotNull(message = "price should not be empty")
    @PositiveOrZero(message = "product price should be 0 or more")
    private double price;

    @NotEmpty(message = "You should add a Category ID")
    String categoryID;

}
