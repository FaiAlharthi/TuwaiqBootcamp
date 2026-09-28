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
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Owner id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer ownerId;

    @NotEmpty(message = "Title is required")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Size(max = 650, message = "Description must not exceed 650 characters")
    @Column(columnDefinition = "VARCHAR(650)")
    private String description;

    @NotEmpty(message = "City is required")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "City must contain letters only")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String city;

    @NotEmpty(message = "Address is required")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String address;

    @NotNull(message = "Price per day is required")
    @Positive(message = "Price per day must be greater than zero")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double pricePerDay;

    @NotNull(message = "Space size is required")
    @Positive(message = "Space size must be greater than zero (in square meters)")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double spaceSize;

    @NotEmpty(message = "Status is required")
    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "Status must be AVAILABLE or UNAVAILABLE")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String status;

    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", insertable = false)
    private LocalDateTime createdAt;
}
