package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    getAllUsers
//    addUser
//    updateUser
//    deleteUser

    //CRUD
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean added = userService.addUser(user);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("new user added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(" user couldn't be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean updated = userService.updateUser(id, user);
        if (updated) {
            return ResponseEntity.status(200).body(new ApiResponse(" user updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(" user couldn't be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        Boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.status(200).body(new ApiResponse(" user deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(" user couldn't be deleted"));
    }
}
