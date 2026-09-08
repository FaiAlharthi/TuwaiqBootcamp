package com.example.day1question1.Controller;

import com.example.day1question1.Api.apiResponse;
import com.example.day1question1.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/task-project/v1")

public class taskController {

    ArrayList<Task> tasks = new ArrayList<>();

    //READ Methods

    //to display all tasks
    @GetMapping("/listTasks")
    public ArrayList<Task> listTasks(){
        return tasks;
    }

    //to search for a task by title
    @GetMapping("/searchTasks/{title}")
    public Task searchTask(@PathVariable String title){
        for(Task task: tasks){
            if(task.getTitle().equalsIgnoreCase(title)){
                return task;
            }
        }
        return null;
    }


    //CREATE Methods

    //to Create new task
    @PostMapping("/create")
    public apiResponse createTask(@RequestBody Task task){
        tasks.add(task);
        return new apiResponse("Task Created Successfully");
    }


    //UPDATE Methods

    //alter a task
    @PutMapping("/update/{index}")
    public apiResponse update(@PathVariable int index, @RequestBody Task task){
        tasks.set(index,task);
        return new apiResponse("Task Altered Successfully");
    }


    //DELETE Methods

    //to delete a task
    @DeleteMapping("/delete/{index}")
    public apiResponse delete(@PathVariable int index){
        tasks.remove(index);
        return new apiResponse("Task Deleted Successfully");
    }






}
