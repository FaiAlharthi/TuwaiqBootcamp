package com.example.day4exercise.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10) not null")
    private String area;

    @Column(columnDefinition = "VARCHAR(10) not null")
    private String street;
    
    @Column(columnDefinition = "VARCHAR(5) not null")
    private String buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}
