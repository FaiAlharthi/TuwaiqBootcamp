package com.example.day1question1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Task {
    private int ID;
    private String title;
    private String description;
    private boolean isDone;
    private String status;
    public static int idCounter =0;


    public Task( String title, String description, boolean isDone) {
        idCounter +=1;
        this.ID = idCounter;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.status=isDone? "Done": "not Done";
    }

}
