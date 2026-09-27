package com.example.packup.Controller;

import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.User;
import com.example.packup.Service.UserService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
        if(userService.getAllUsers().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No Users"));
        }
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if(userService.createUser(user)){
            return ResponseEntity.status(200).body(new ApiResponse("new user added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Phone number already exists"));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int updated= userService.updateUser(id,user);
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No user with this Id"));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("Phone number already exists bu another user"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("user information updated successfully"));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No user with this Id"));
    }

    @PutMapping("/blockRenter/{ownerId}/{blockedRenter}")
    public ResponseEntity<?> blockRenter(@PathVariable Integer ownerId, @PathVariable Integer blockedRenter){
        int result = userService.blockRenter(ownerId, blockedRenter);
        if(result == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No owner with this id"));
        }
        if(result == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No renter with this id"));
        }
        if(result == -3){
            return ResponseEntity.status(400).body(new ApiResponse("This renter is already blocked"));
        }
        if(result == -4){
            return ResponseEntity.status(400).body(new ApiResponse("Cannot block a renter with active booking (BOOKED or ONGOING)"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Renter blocked successfully"));
    }

    @GetMapping("/getBlockedRenters/{ownerId}")
    public ResponseEntity<?> getBlockedRenters(@PathVariable Integer ownerId){
        List<Integer> renters = userService.getBlockedRenters(ownerId);
        if(renters == null){
            return ResponseEntity.status(400).body(new ApiResponse("No owner with this id"));
        }
        if(renters.isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("This owner has not blocked any renters"));
        }
        return ResponseEntity.status(200).body(renters);
    }


}
