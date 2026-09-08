package com.example.day3exercise.Controller;

import com.example.day3exercise.Api.ApiResponse;
import com.example.day3exercise.Model.Project;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/project/v1")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    //display all projects
    @GetMapping("/displayAll")
    public ResponseEntity<?> displayAll(){
        return ResponseEntity.status(200).body(projects);
    }

    //display project by given title
    @GetMapping("/searchTitle")
    public ResponseEntity<?> displayTitle(@RequestBody @Valid String title, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(project);
            }
        }
        return null;
    }


    //display all projects for a certain company
    @GetMapping("/companyProjs")
    public ResponseEntity<?> displayCompProjs (@RequestBody @Valid String companyName, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        ArrayList<Project> newProjects = new ArrayList<>();
        for(Project project : projects){
            if(project.getCompanyName().equalsIgnoreCase(companyName)){
                newProjects.add(project);
            }
        }
        return ResponseEntity.status(200).body(newProjects);
    }


    //create new project
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project created successfully"));
    }

    //update a project
    @PutMapping("/update/{index}")
    public ResponseEntity<?> update(@PathVariable int index, @RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(index >= projects.size() || index < 0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }
        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));

    }

    //update project status
    @PutMapping("/updateStatus/{index}")
    public ResponseEntity<?> updateStatus(@PathVariable int index, @RequestBody String status){
        if(index >= projects.size() || index < 0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }
        if(projects.get(index).getStatus().equalsIgnoreCase("Not Started") && (! (status.equalsIgnoreCase("In Progress"))) ){
            return ResponseEntity.status(400).body(new ApiResponse("the next status for this project has to be In Progress "));
        }
        if(projects.get(index).getStatus().equalsIgnoreCase("In Progress") && !(status.equalsIgnoreCase("Completed"))) {
            return ResponseEntity.status(400).body(new ApiResponse("the next status for this project has to be Completed "));
        }
        if(projects.get(index).getStatus().equalsIgnoreCase("Completed")){
            return ResponseEntity.status(400).body(new ApiResponse("the project is already Completed and cannot be changed "));
        }

        projects.get(index).setStatus(status);
        return ResponseEntity.status(200).body("Status successfully changed");
    }


    //delete a project
    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> delete(@PathVariable int index){
        if(index >= projects.size() || index < 0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("project is deleted"));
    }
}
