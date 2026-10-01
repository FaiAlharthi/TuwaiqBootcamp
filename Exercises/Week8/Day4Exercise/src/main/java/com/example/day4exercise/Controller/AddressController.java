package com.example.day4exercise.Controller;

import com.example.day4exercise.Api.ApiResponse;
import com.example.day4exercise.DTO.AddressDTO;
import com.example.day4exercise.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/address")
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAddress(){
        return ResponseEntity.status(200).body(addressService.getAllAddress());
    }

    @PostMapping("/createAddress")
    public ResponseEntity<?> createAddress(@RequestBody@Valid AddressDTO addressDTO){
        addressService.createAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("new address added"));
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<?> updateAddress(@RequestBody@Valid AddressDTO addressDTO){
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("new address updated"));
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(new ApiResponse("new address deleted"));
    }

}
