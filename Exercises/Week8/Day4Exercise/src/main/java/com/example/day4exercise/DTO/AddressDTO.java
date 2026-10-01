package com.example.day4exercise.DTO;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private Integer teacher_id;

    @NotEmpty(message = "area cannot be empty")
    private String area;

    @NotEmpty(message = "street cannot be empty")
    private String street;

    @NotEmpty(message = "buildingNumber cannot be empty")
    private String buildingNumber;

}
