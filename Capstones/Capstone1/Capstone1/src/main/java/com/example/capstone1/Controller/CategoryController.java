package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/category/v1")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAllCats")
    public ResponseEntity<?> getCategories(){
        if(categoryService.getCategories().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Categories to show"));
        }
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        int response = categoryService.addCategory(category);
        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Category ID is already in use."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Category already exists."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Category " + category.getName() + " has been added successfully"));
    }

    @PutMapping("/updateCat/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody @Valid Category category, Errors errors){
        int updated = categoryService.updateCategory(id,category);

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Category ID is already in use."));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Category already exists."));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Category not found."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Category has been updated successfully."));
    }

    @DeleteMapping ("/deleteCat/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id){
        boolean deleted = categoryService.deleteCategory(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Category has been deleted successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found."));
    }
}