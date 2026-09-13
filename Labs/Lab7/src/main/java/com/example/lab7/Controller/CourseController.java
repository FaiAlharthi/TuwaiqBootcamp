package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/v1")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService = new CourseService();

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses(){
        if(courseService.getAllCourses() == null){
            return ResponseEntity.status(400).body(new ApiResponse("No courses to be displayed"));
        }
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse (@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean created = courseService.createCourse(course);
        if(created){
            return ResponseEntity.status(200).body(new ApiResponse("course created successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("courses couldn't be created"));
    }

    @PutMapping("/updateCourse/{index}")
    public ResponseEntity<?> updateCourse (@PathVariable int index, @RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated = courseService.updateCourse(index,course);
        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("index is not found"));
    }

    @DeleteMapping ("/deleteCourse/{index}")
    public ResponseEntity<?> deleteCourse (@PathVariable int index){
        boolean deleted = courseService.deleteCourse(index);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("index is not found"));
    }

    @PutMapping("/addAnnouncement/{index}")
    public ResponseEntity<?> addAnnouncement(@PathVariable int index, @RequestBody String announcement){
        boolean added = courseService.addAnnouncement(index, announcement);

        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("announcement added successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("announcement couldn't be added"));
    }

    @GetMapping("/getInstructor/{courseCode}")
    public ResponseEntity<?> getInstructor (@PathVariable String courseCode){
        String instructor = courseService.getInstructor(courseCode);
        if(instructor == null){
            return ResponseEntity.status(400).body(new ApiResponse("No Courses with this code OR name"));
        }
        return ResponseEntity.status(200).body(instructor);
    }

    @GetMapping("/getExams/{index}")
    public ResponseEntity<?> getExams (@PathVariable int index){
        int nOfExams = courseService.getExams(index);

        if(nOfExams == -1){
            return ResponseEntity.status(400).body(new ApiResponse("invalid index"));
        }
        return ResponseEntity.status(200).body(nOfExams);
    }

    @PutMapping("/updateExams/{index}/{newNoExams}")
    public ResponseEntity<?> updateExams (@PathVariable int index, @PathVariable int newNoExams){
        boolean updated = courseService.updateExams(index,newNoExams);

        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("number of exams for this course updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Courses with this index, Or Make sure number of exams exceeds 2"));
    }





}
