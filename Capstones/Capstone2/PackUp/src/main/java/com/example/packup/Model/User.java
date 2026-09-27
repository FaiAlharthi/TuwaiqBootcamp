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

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be 10 digits")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String phone;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
    @Column(columnDefinition = "VARCHAR(15) NOT NULL")
    private String password;

    @NotEmpty(message = "Full name is required")
    @Size(max = 20, message = "Full name must not exceed 20 characters")
    @Pattern(regexp = "^.*\\S.*$", message = "Full name must contain at least one letter, number, or symbol")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String fullName;

    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", insertable = false)
    private LocalDateTime createdAt;

}
