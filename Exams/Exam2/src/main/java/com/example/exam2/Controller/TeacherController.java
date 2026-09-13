package com.example.exam2.Controller;


import com.example.exam2.Api.ApiResponse;
import com.example.exam2.Model.Teacher;
import com.example.exam2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

@RestController
@RequestMapping("api/teacher/v1")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService = new TeacherService();

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTeachers(){
        if(teacherService.getAllTeachers().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Teachers until now"));
        }
        return ResponseEntity.status(200).body(teacherService);
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<?> addNewTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean added = teacherService.addNewTeacher(teacher);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("techer added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("techer couldn't be added"));

    }

    @PutMapping("/updateTeacher/{index}")
    public ResponseEntity<?> updateTeacher ( @PathVariable int index, @RequestBody @Valid Teacher teacher , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated = teacherService.updateTeacher(index, teacher);

        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("teacher updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher couldn't be updated "));
    }

    @DeleteMapping("/deleteTeacher/{index}")
    public ResponseEntity<?> deleteTeacher ( @PathVariable int index){
        boolean deleted = teacherService.deleteTeacher(index);

        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher couldn't be deleted"));
    }

    @GetMapping("/searchID/{id}")
    public ResponseEntity<?> TeacherByID(@PathVariable String id){
        Teacher foundedTeacher = teacherService.TeacherByID(id);
        if(foundedTeacher == null){
            return ResponseEntity.status(400).body(new ApiResponse("No Teacher with this id !"));
        }
        return ResponseEntity.status(200).body(foundedTeacher);
    }

    @GetMapping("/searchSalaries/{salary}")
    public ResponseEntity<?> TeachersBySalary(@PathVariable double salary){
        ArrayList<Teacher> higherSalaries = teacherService.TeachersBySalary(salary);
        if(higherSalaries.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No teachers have equal or higher than this salary "));
        }
        return ResponseEntity.status(200).body(higherSalaries);
    }
}
