package com.example.day4exercise.Controller;

import com.example.day4exercise.Api.ApiResponse;
import com.example.day4exercise.Model.Student;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTeachers(){
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/createStudent")
    public ResponseEntity<?> createStudent(@RequestBody @Valid Student student){
        studentService.createStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("new student added"));
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id,@RequestBody@Valid Student student){
        studentService.updateStudent(id,student);
        return ResponseEntity.status(200).body(new ApiResponse("new student updated"));
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("new student deleted"));
    }

}
