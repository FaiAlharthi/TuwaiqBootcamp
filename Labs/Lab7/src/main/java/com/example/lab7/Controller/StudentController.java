package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
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
    final private StudentService studentService = new StudentService();

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added successfully"));
    }

    @PutMapping("/updateStudent/{index}")
    public ResponseEntity<?> updateStudent( @PathVariable int index ,@RequestBody @Valid Student student, Errors errors){
        boolean updated = studentService.updateStudent(index,student);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("student couldn't be updated"));
    }

    @DeleteMapping("/deleteStudent/{index}")
    public ResponseEntity<?> deleteStudent( @PathVariable int index ){
        boolean deleted = studentService.deleteStudent(index);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("student couldn't be deleted"));
    }


    @GetMapping("/listCourses/{studentID}")
    public ResponseEntity<?> listCourses(@PathVariable String studentID){
        ArrayList<Course> studentCourses = studentService.listCourses(studentID);
        if(studentID.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Wrong ID"));
        }
        return ResponseEntity.status(200).body(studentCourses);
    }

    @PutMapping("/addCourse/{index}")
    public ResponseEntity<?> addNewCourse(@PathVariable int index, @RequestBody @Valid Course course){
        boolean isAdded = studentService.addNewCourse(index,course);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("course registered successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("course coudldn't be registered"));

    }

    @PutMapping("/addGrade/{studentID}/{CourseCode}/{finalGrade}")
    public ResponseEntity<?> addGrade (@PathVariable String studentID,@PathVariable String CourseCode, @PathVariable double finalGrade){
        boolean isAdded = studentService.addGrade(studentID,CourseCode, finalGrade);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Grade added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("grade couldn't be added"));

    }


}
