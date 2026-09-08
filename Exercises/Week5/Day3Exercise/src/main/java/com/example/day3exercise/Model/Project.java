package com.example.day3exercise.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID should not be empty")
    @Size(min = 3, message = "ID should be more than 2 characters")
    private String ID;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 9, message = "title should be more than 8 characters")
    private String title;

    @NotEmpty(message = "description should not be empty")
    @Size(min = 16, message = "Description should be more than 15 chars")
    private String description;

    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "the status should be only ( Not Started, OR In Progress, OR Completed )")
    private String status;

    @NotEmpty(message = "company Name should not be empty")
    @Size(min = 7, message = "company Name should be more than 6 characters")
    private String companyName;

}
