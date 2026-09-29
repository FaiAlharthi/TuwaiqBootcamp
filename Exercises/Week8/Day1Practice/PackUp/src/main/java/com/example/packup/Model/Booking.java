package com.example.packup.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Space id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer spaceId;

    @NotNull(message = "Renter id is required")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer renterId;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or later")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be today or later")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate endDate;

    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double totalPrice;

    @NotNull(message = "paid Amount is required")
    @Positive(message = "paid Amount must be greater than zero")
    @Column(columnDefinition = "Double Not Null")
    private Double paidAmount;

    @Pattern(regexp = "BOOKED|ONGOING|CANCELLED|COMPLETED", message = "Status must be BOOKED, CANCELLED, or COMPLETED")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String status;

    @CreationTimestamp
    @Column(columnDefinition = "dateTime DEFAULT CURRENT_TIMESTAMP Not null", updatable = false)
    private LocalDateTime createdAt;


}