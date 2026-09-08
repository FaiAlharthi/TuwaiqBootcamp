package com.example.lab5.Controller;

import com.example.lab5.Api.ApiResponse;
import com.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/student/v1")
public class studentController {
    ArrayList<Student> students = new ArrayList<>();

    //CRUD
    // READ Methods
    @GetMapping("/displayAll")
    public ArrayList<Student> displayAll(){
        return students;
    }

    @GetMapping("greaterAvg")
    public ArrayList<Student> greaterAvgGPA(){
        double avg;
        double sum =0;
        ArrayList<Student> newStudents = new ArrayList<>();
        for(Student student : students){
            sum = student.getGPA()+sum;
        }
        avg = sum/students.size();
        for(Student student : students){
            if(student.getGPA()> avg){
                newStudents.add(student);
            }
        }
        return newStudents;
    }

    @GetMapping("/honor/{GPA}")
    public ApiResponse honorClass(@PathVariable double GPA){
        if (GPA >= 4.75){
            return new ApiResponse("You Are a First Honor ");
        }else if (GPA >= 4.25){
            return new ApiResponse("You Are a seconde honor class");
        }
        return new ApiResponse("You are not Honor Class");
    }

    //CREATE Methods
    @PostMapping("/create")
    public ApiResponse create (@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    //UPDATE Methods
    @PutMapping("/update/{index}")
    public ApiResponse update(@PathVariable int index, @RequestBody Student student){
        students.set(index,student);
        return new ApiResponse("student info edited successfully");
    }

    //DELETE Methods
    @DeleteMapping("/delete/{index}")
    public ApiResponse delete (@PathVariable int index){
        students.remove(index);
        return new ApiResponse("student info deleted successfully");
    }

    //Extra Endpoints

    //to display student older than a specific age
    @GetMapping("/olderThan/{age}")
    public ArrayList<Student> getAge(@PathVariable int age){
        ArrayList<Student> newStudents = new ArrayList<>();
        for (Student student : students){
            if(student.getAge() > age){
                newStudents.add(student);
            }
        }
        return newStudents;
    }

    @PutMapping("/editName/{index}/{name}")
    public ApiResponse editName(@PathVariable int index ,@PathVariable String name){
        students.get(index).setName(name);
        return new ApiResponse("name editted successfully");
    }



}
