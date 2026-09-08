package com.example.lab5question2.Controller;

import com.example.lab5question2.Api.ApiResponse;
import com.example.lab5question2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/project/v1")
public class projectController {
    ArrayList<Project> projects = new ArrayList<>();


    //READ Methods
    @GetMapping("/displayAll")
    public ArrayList<Project> displayAll(){
        return projects;
    }

    @GetMapping("/searchTitle/{title}")
    public Project searchTitle (@PathVariable String title){
        for(Project project : projects){
            if (project.getTitle().equalsIgnoreCase(title)){
                return project;
            }
        }
        return null;
    }

    @GetMapping("/companyProjs/{company}")
    public ArrayList<Project> allCompanyProjs(@PathVariable String company){
        ArrayList<Project> newProjects = new ArrayList<>();
        for(Project project:projects){
            if(project.getCompanyName().equalsIgnoreCase(company)){
                newProjects.add(project);
            }
        }
        return newProjects;
    }

    //CREATE Methods
    @PostMapping("/create")
    public ApiResponse create (@RequestBody Project project){
        if(project.getStatus().trim().equalsIgnoreCase("done")||project.getStatus().equalsIgnoreCase("not done")){
            projects.add(project);
            return new ApiResponse("project added successfully");
        }
        return new ApiResponse("you need to set the status as done or not done");
    }

    //UPDATE Methods
    @PutMapping("/update/{index}")
    public ApiResponse update(@PathVariable int index, @RequestBody Project project){
        projects.set(index,project);
        return new ApiResponse("project info updated successfully");
    }

    @PutMapping("/updateStatus/{index}/{status}")
    public ApiResponse updateStatus(@PathVariable int index,@PathVariable String status){
       projects.get(0).setStatus(status);
       return new ApiResponse("Project status updated successfully");
    }

    //DELETE
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new ApiResponse("project deleted successfully");
    }


    //Extra Endpoints
    @GetMapping("/relatedPs/{word}")
    public ArrayList<Project> relatedP(@PathVariable String word){
        ArrayList<Project> newProjects = new ArrayList<>();
        for(Project project : projects){
            if(project.getDescription().contains(word) || project.getTitle().contains(word)){
                newProjects.add(project);
            }
        }
        return newProjects;
    }

    @PutMapping("/updateDesc/{index}")
    public ApiResponse updateDesc(@PathVariable int index, @RequestBody String desc){
        projects.get(index).setDescription(desc);
        return new ApiResponse("Description updated successfully");
    }


}
