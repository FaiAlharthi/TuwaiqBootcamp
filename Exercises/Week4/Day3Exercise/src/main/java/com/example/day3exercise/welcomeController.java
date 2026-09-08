package com.example.day3exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {

    @GetMapping("/name")
    public String name(){
        return "My name is Fai";
    }

    @GetMapping("/age")
    public String age(){
        return "My age is 23";
    }

    @GetMapping("/status")
    public String check(){
        return "Everything is OK";
    }

    @GetMapping("health")
    public String health(){
        return "Server health is Up and Running";
    }

    @GetMapping("names")
    public String[] names(){
        String [] names = {"Fai","Reem", "noura"};
        return names;
    }
}
