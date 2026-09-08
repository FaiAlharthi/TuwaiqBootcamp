package com.example.day1question2.Controller;

import com.example.day1question2.Api.ApiResponse;
import com.example.day1question2.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/bank/v1")

public class customerController {
    ArrayList<Customer> customers = new ArrayList<>();

    //READ Methods
    @GetMapping("/listAll")
    public ArrayList<Customer> listCustomers(){
        return customers;
    }

    //CREATE Methods
    @PostMapping("/create")
    public ApiResponse create(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Customer Added Successfully");
    }

    //UPDATE Methods
    @PutMapping("put/{index}")
    public ApiResponse update(@PathVariable int index, @RequestBody Customer customer){
        customers.set(index, customer);
        return new ApiResponse("Customer Altered Successfully");
    }

    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index, @RequestBody Double amount){
        customers.get(index).setBalance( (customers.get(index).getBalance()+amount) );
        return new ApiResponse("money deposited successfully");
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index, @RequestBody Double amount){
        if(customers.get(index).getBalance() > amount){
            customers.get(index).setBalance( (customers.get(index).getBalance()-amount) );
            return new ApiResponse("withdraw successfully done");
        }else{
            return new ApiResponse("You don't have enough money");
        }
    }

    //DELETE Methods
    @DeleteMapping("/delete/{index}")
    public ApiResponse delete(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Customer Deleted Successfully");
    }
}
