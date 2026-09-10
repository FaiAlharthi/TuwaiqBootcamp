package com.example.day5exercise.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Article {

    @NotEmpty(message = "id cannot be empty")
    private String id;

    @NotEmpty(message = "title cannot be empty")
    @Size(max = 100, message = "max length of title is 100 letter")
    private String title;

    @NotEmpty(message = "auther cannot be empty")
    @Size(min = 5, max = 20, message = "auther name should be between 5 to 20 letter max")
    private String auther;

    @NotEmpty(message = "you need to add content")
    @Size(min = 200, message = "content sbould be more than 200 letter")
    private String content;

    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "^(politics|Sports|technology)$")
    private String category;

    @NotEmpty(message = "you need to write image URL")
    private String imageURL;

    @AssertFalse
    private boolean isPublished;

    @FutureOrPresent
    @NotNull(message = "you have to add publish date")
    private LocalDate publishDate;
}
