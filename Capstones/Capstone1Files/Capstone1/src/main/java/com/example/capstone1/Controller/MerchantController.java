package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant/v1")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/getMerchants")
    public ResponseEntity<?> getMerchants(){
        if(merchantService.getMerchants().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Merchants to show"));
        }
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/addMerchant")
    public ResponseEntity<?> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean response = merchantService.addMerchant(merchant);
        if(response){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant " + merchant.getName() + " has been added successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant ID is already in use."));

    }

    @PutMapping("/updateMerchant/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors){
        int updated = merchantService.updateMerchant(id,merchant);

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant ID is already in use."));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Merchant has been updated successfully."));
    }

    @DeleteMapping ("/deleteMerchant/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable String id){
        boolean deleted = merchantService.deleteMerchant(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant has been deleted successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
    }

}