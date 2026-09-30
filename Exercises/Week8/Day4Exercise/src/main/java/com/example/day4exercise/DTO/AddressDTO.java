package com.example.day4exercise.DTO;

import com.example.day4exercise.Model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private Integer teacher_id;

    @NotEmpty(message = "area cannot be empty")
    @Column(columnDefinition = "VARCHAR(10) not null")
    private String area;

    @NotEmpty(message = "street cannot be empty")
    @Column(columnDefinition = "VARCHAR(10) not null")
    private String street;

    @NotEmpty(message = "buildingNumber cannot be empty")
    @Column(columnDefinition = "VARCHAR(5) not null")
    private String buildingNumber;

}
