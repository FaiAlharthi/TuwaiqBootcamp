package com.example.exam2.Controller;


import com.example.exam2.Api.ApiResponse;
import com.example.exam2.Model.Student;
import com.example.exam2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/student/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService = new StudentService();

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStudents (){
        if(studentService.getAllStudents().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No students until now"));
        }
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addNewStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean added = studentService.addNewStudent(student);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student couldn't be added"));
    }

    @PutMapping("/updateStudent/{index}")
    public ResponseEntity<?> updateStudent (@PathVariable int index, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated = studentService.updateStudent(index, student);
        if(updated){
                    return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
                }
        return ResponseEntity.status(400).body(new ApiResponse("Student index couldn't be found"));
    }

    @DeleteMapping("/deleteStudent/{index}")
    public ResponseEntity<?> deleteStudent (@PathVariable int index){
        boolean deleted = studentService.deleteStudent(index);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student index couldn't be found"));
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> returnStudentByName (@PathVariable String name){
        Student foundedStudent = studentService.returnStudentByName(name);

        if(foundedStudent == null){
            return ResponseEntity.status(400).body(new ApiResponse(" No Student with this name !"));
        }
        return ResponseEntity.status(200).body(foundedStudent);

    }

    @GetMapping("/searchMajor/{major}")
    public ResponseEntity<?> returnMajorStudent (@PathVariable String major){
        ArrayList<Student> majorStudents = new ArrayList<>();
        majorStudents = studentService.returnMajorStudent(major);

        if(majorStudents.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse(" No Students in this major !"));
        }
        return ResponseEntity.status(400).body(majorStudents);
    }



}
