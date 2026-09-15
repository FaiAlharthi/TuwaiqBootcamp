package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/product/v1")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        if(productService.getProducts().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Products to show"));
        }

        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        int response = productService.addProducts(product);
        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Product ID is already in use."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Category not found."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Product has been added successfully."));

    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        int updated = productService.updateProduct(id,product);
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Product ID is already in use."));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Category not found."));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Product has been updated successfully."));
    }

    @DeleteMapping ("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean deleted = productService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product has been deleted successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
    }

    @GetMapping("/productsInRange/{categoryID}/{min}/{max}")
    public ResponseEntity<?> productsInRange(@PathVariable String categoryID, @PathVariable double min, @PathVariable double max) {
        ArrayList<Product> products = productService.productsInRange(categoryID,min,max);
        if(products == null){
            return ResponseEntity.status(400).body(new ApiResponse("Minimum price must be 0 or higher and less than or equal to the maximum price."));
        }
        if(products.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No products found in this category within the specified price range."));
        }
        return ResponseEntity.status(200).body(products);

    }

}