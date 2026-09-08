package com.example.day3exerciseq2.Controller;

import com.example.day3exerciseq2.Api.ApiResponse;
import com.example.day3exerciseq2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/event/v1")

public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    //display All events
    @GetMapping("/display")
    public ResponseEntity<?> displayAll(){
        return ResponseEntity.status(200).body(events);
    }

    //search for event by id
    @GetMapping("/displayByID/{ID}")
    public ResponseEntity<?> searchID(@PathVariable String ID){
        for(Event event: events){
            if(event.getID().equalsIgnoreCase(ID)){
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("No event with this ID"));
    }

    //create new event
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Event event, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(!(event.getStartDate().isBefore(event.getEndDate()))){
            return ResponseEntity.status(400).body(new ApiResponse("End Date cannot be Before Start Date !"));
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event created successfully"));

    }

    //update event
    @PutMapping("/update/{index}")
    public ResponseEntity<?> update(@PathVariable int index, @RequestBody @Valid Event event, Errors errors){
        if(!(events.get(index).getStartDate().isBefore(events.get(index).getEndDate()))){
            return ResponseEntity.status(400).body(new ApiResponse("End Date cannot be Before Start Date !"));
        }
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(index >= events.size() || index<0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("event updated Successfully"));
    }

    //change capacity
    @PutMapping("/updateCapacity/{index}")
    public ResponseEntity<?> changeCapacity(@PathVariable int index, @RequestBody int capacity){
        if(index >= events.size() || index<0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }

        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("event capacity updated Successfully"));

    }

    //delete event
    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> delete(@PathVariable int index){
        if(index >= events.size() || index<0){
            return ResponseEntity.status(400).body(new ApiResponse("index not exist"));
        }

        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event is deleted "));

    }


}
