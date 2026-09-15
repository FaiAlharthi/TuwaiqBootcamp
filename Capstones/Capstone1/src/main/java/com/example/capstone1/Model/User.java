package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 6, message = "username should be more than 5 characters")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 6, message = "password should be more than 5 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and digits are allowed")
    private String password;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "email format is invalid")
    private String email;

    @NotEmpty(message = "role is required")
    @Pattern(regexp = "^(customer|admin)$", message = "Role must be either 'customer' or 'admin'")
    private String role;

    @NotNull(message = "balance is required")
    @PositiveOrZero(message = " balance should be 0 or more ")
    private double balance;


}
