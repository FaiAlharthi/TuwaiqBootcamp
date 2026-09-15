package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = " product id should not be empty")
    private String productId;

    @NotEmpty(message = " merchant id should not be empty")
    private String merchantID;

    @NotNull(message = "stock cannot be empty")
    @Min(value = 10, message = "stock should starts from 10")
    private int stock;

}
