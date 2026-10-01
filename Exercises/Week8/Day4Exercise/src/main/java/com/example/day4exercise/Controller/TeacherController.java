package com.example.day4exercise.Controller;

import com.example.day4exercise.Api.ApiResponse;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/getAll")
    public ResponseEntity<?>getAllTeachers(){
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody@Valid Teacher teacher){
        teacherService.createTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("new teacher added"));
    }

    @PutMapping("/updateTeacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Integer id,@RequestBody@Valid Teacher teacher){
        teacherService.updateTeacher(id,teacher);
        return ResponseEntity.status(200).body(new ApiResponse("new teacher updated"));
    }

    @DeleteMapping("/deleteTeacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("new teacher deleted"));
    }

    @GetMapping("/getTeacherInfo/{id}")
    public ResponseEntity<?> getTeacherInfo(@PathVariable Integer id){
        Teacher teacher = teacherService.getTeacherInfo(id);
        return ResponseEntity.status(200).body(teacher);
    }
}
