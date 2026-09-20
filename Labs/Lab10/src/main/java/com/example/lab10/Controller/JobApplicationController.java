package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobapplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    //CRUD
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllJobApplications(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJobApplication(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean added = jobApplicationService.addJobApplication(jobApplication);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("new job application added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job application couldn't be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJobApplication(@PathVariable Integer id, @RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean updated = jobApplicationService.updateJobApplication(id, jobApplication);
        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("job application updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job application couldn't be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJobApplication(@PathVariable Integer id){
        Boolean deleted = jobApplicationService.deleteJobApplication(id);
        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("job application deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job application couldn't be deleted"));
    }
}