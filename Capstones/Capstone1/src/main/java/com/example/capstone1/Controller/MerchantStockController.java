package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchantStock/v1")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/getStocks")
    public ResponseEntity<?> getMerchantStocks(){
        if(merchantStockService.getMerchantStocks().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Stocks to show"));
        }
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/addStock")
    public ResponseEntity<?> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int response = merchantStockService.addMerchantStock(merchantStock);

        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Stock ID is already in use."));
        }

        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        if(response == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }
        if(response == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This merchant already has this product in stock."));

        }

        return ResponseEntity.status(200).body(new ApiResponse("Merchant stock has been added successfully."));

    }

    @PutMapping("/updateEntireStock/{id}")
    public ResponseEntity<?> updateEntireStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        int updated = merchantStockService.updateEntireStock(id,merchantStock);

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Stock ID is already in use."));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }
        if(updated == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This merchant already has this product in stock."));
        } if(updated == -5){
            return ResponseEntity.status(400).body(new ApiResponse("Stock not found."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Stock has been updated successfully."));
    }

    @DeleteMapping ("/deleteStock/{id}")
    public ResponseEntity<?> deleteMerchantStock(@PathVariable String id){
        boolean deleted = merchantStockService.deleteMerchantStock(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Stock has been deleted successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Stock not found."));
    }


    @PutMapping("/updateStock/{productID}/{merchantID}/{stock}")
    public ResponseEntity<?> updateStock(@PathVariable String productID, @PathVariable String merchantID, @PathVariable int stock){
        int response = merchantStockService.updateStock(productID,merchantID,stock);
        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }
        if(response == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Stock quantity must be greater than 10."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Stock has been updated successfully."));
    }


    @PutMapping("/applyDiscount/{merchantID}/{productID}/{discountPercentage}")
    public ResponseEntity<?> applyDiscount(@PathVariable String merchantID, @PathVariable String productID, @PathVariable int discountPercentage){
        int response = merchantStockService.applyDiscount(merchantID,productID,discountPercentage);

        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid product ID."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid merchant ID."));
        }
        if(response == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Discount percentage must be 5% or higher."));
        }
        if(response == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This merchant does not sell this product."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Discount has been applied successfully."));
    }


}