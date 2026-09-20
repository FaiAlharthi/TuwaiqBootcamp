package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    //CRUD
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean added = jobPostService.addJobPost(jobPost);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("new job post added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job post couldn't be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean updated = jobPostService.updateJobPost(id, jobPost);
        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("job post updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job post couldn't be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer id){
        Boolean deleted = jobPostService.deleteJobPost(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("job post deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job post couldn't be deleted"));
    }
}