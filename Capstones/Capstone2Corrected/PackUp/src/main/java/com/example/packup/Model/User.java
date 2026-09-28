package com.example.packup.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Full name is required")
    @Size(max = 20, message = "Full name must not exceed 20 characters")
    @Pattern(regexp = "^.*\\S.*$", message = "Full name must contain at least one letter, number, or symbol")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String fullName;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String phone;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 7, max = 15, message = "Password must be between 7 and 15 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one symbol"
    )
    @Column(columnDefinition = "VARCHAR(15) NOT NULL")
    private String password;

    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", insertable = false)
    private LocalDateTime createdAt;

}
