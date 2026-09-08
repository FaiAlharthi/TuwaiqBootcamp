package com.example.lab5question3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

@Data
@AllArgsConstructor
public class Event {
    private String ID;
    private String description;
    private int capacity;
    private LocalDate startDate,endDate;


}
