package com.example.day4exercise.Controller;

import com.example.day4exercise.Api.ApiResponse;
import com.example.day4exercise.Model.Course;
import com.example.day4exercise.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody @Valid Course course){
        courseService.createCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("course created successfully"));
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course){
        courseService.updateCourse(id,course);
        return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
    }

    @GetMapping("/teacherOfCourse/{course_id}")
    public ResponseEntity<?> teacherOfCourse(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(courseService.teacherOfCourse(course_id));
    }
}
