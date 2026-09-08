package com.example.day1question2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String ID;
    private String userName;
    private Double balance;
}
