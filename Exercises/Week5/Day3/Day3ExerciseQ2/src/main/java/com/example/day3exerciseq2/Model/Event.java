package com.example.day3exerciseq2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "Event cannot be empty !")
    @Size(min = 3, message = "Event ID should be more than 2 letters")
    private String ID;

    @NotEmpty(message = "Description cannot be empty !")
    @Size(min = 16, message = "Description should be more than 15 letters")
    private String description;

    @NotNull(message = "Capacity cannot be empty !")
    @Positive(message = "Capacity must be a positive number !")
    @Min(value = 26, message = "Capacity must be more than 25 !")
    private Integer capacity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "The Event must be today or in the future")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "The Event must be today or in the future")
    private LocalDateTime endDate;
}
