package com.example.packup.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Booking id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer bookingId;

    @NotNull(message = "Reviewer id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer reviewerId;

    @NotNull(message = "Reviewee id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer revieweeId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Minimum rating is 1")
    @Max(value = 5, message = "Maximum rating is 5")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer rating;

    @Size(max = 500, message = "Comment must not exceed 500 characters")
    @Column(columnDefinition = "VARCHAR(500)")
    private String comment;

    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", insertable = false)
    private LocalDateTime createdAt;
}