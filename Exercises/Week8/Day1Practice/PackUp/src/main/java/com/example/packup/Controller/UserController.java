package com.example.packup.Controller;

import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.User;
import com.example.packup.Service.UserService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //CRUD
    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers (){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user){
        userService.createUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("new user added successfully"));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user){
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("user information updated successfully"));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @PutMapping("/blockRenter/{ownerId}/{blockedRenter}")
    public ResponseEntity<?> blockRenter(@PathVariable Integer ownerId, @PathVariable Integer blockedRenter){
        userService.blockRenter(ownerId, blockedRenter);
        return ResponseEntity.status(200).body(new ApiResponse("Renter blocked successfully"));
    }

    @GetMapping("/getBlockedRenters/{ownerId}")
    public ResponseEntity<?> getBlockedRenters(@PathVariable Integer ownerId){
        List<Integer> renters = userService.getBlockedRenters(ownerId);
        return ResponseEntity.status(200).body(renters);
    }


}
