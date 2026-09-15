package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
        if(userService.getUsers().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Users to show"));
        }

        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        int response = userService.addUser(user);
        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("User ID is already in use."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("An account with this email already exists."));
        }
        if(response == -3) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin balance must be zero."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("User " + user.getUsername() + " has been added successfully."));

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors){
        int updated = userService.updateUser(id,user);

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("User ID is already in use."));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("This email is already in use."));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("User not found."));
        }
        if(updated == -4) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin balance must be zero."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("User has been updated successfully."));
    }

    @DeleteMapping ("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        boolean deleted = userService.deleteUser(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("User has been deleted successfully."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found."));
    }

    @PutMapping("/buyProduct/{userID}/{productID}/{merchantID}")
    public ResponseEntity<?> buyProduct(@PathVariable String userID,@PathVariable String productID,@PathVariable String merchantID ){
        int response = userService.buyProduct(userID,productID,merchantID);

        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("User not found."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        if(response == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }
        if(response == -4){
            return ResponseEntity.status(400).body(new ApiResponse("This merchant does not sell this product."));
        }
        if(response == -5){
            return ResponseEntity.status(400).body(new ApiResponse("This product is currently out of stock."));
        }
        if(response == -6){
            return ResponseEntity.status(400).body(new ApiResponse("Insufficient balance to purchase this product."));
        }
        if(response == -7){
            return ResponseEntity.status(400).body(new ApiResponse("Administrators cannot purchase products."));
        }


        return ResponseEntity.status(200).body(new ApiResponse("Your order has been completed successfully."));
    }

    @PutMapping("/giveBalance/{senderID}/{recipientID}/{amount}")
    public ResponseEntity giveGiftCard(@PathVariable String senderID, @PathVariable String recipientID, @PathVariable double amount){
        int response = userService.giveGiftCard(senderID, recipientID,amount);

        if(response ==-1){
            return ResponseEntity.status(400).body(new ApiResponse("Sender not found."));
        }
        if(response ==-2){
            return ResponseEntity.status(400).body(new ApiResponse("Recipient not found."));
        }
        if(response ==-3){
            return ResponseEntity.status(400).body(new ApiResponse("Insufficient balance for this transaction."));
        }
        if(response == -4){
            return ResponseEntity.status(400).body(new ApiResponse("The recipient cannot be an administrator."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Gift card balance has been sent successfully to the user with ID: " + recipientID));
    }

    @PutMapping("/returnProduct/{userID}/{productID}")
    public ResponseEntity<?> returnProduct(@PathVariable String userID,@PathVariable String productID ){
        int response = userService.returnProduct(userID,productID);

        if(response == -1){
            return ResponseEntity.status(400).body(new ApiResponse("User not found."));
        }
        if(response == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        if(response == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Administrators cannot return products."));
        }
        if(response == -4){
            return ResponseEntity.status(400).body(new ApiResponse("You have not purchased this product."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Product has been returned successfully."));
    }

    @GetMapping ("/PreviousOrders/{userID}")
    public ResponseEntity<?> diplayPreviousOrders (@PathVariable String userID){
        ArrayList<Product> boughtProducts = userService.diplayPreviousOrders(userID);

        if(boughtProducts == null){
            return ResponseEntity.status(400).body(new ApiResponse("User not found."));
        }
        if(boughtProducts.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No previous orders found."));
        }
        return ResponseEntity.status(200).body(boughtProducts);
    }

    @GetMapping("/displayCustomer/{userID}")
    public ResponseEntity<?> displayCustomer(@PathVariable String userID){
        ArrayList<User> customers = userService.displayCustomer(userID);
        if(customers == null){
            return ResponseEntity.status(400).body(new ApiResponse("Only administrators can access this information."));
        }
        return ResponseEntity.status(200).body(customers);
    }

}