package com.example.lab5question3.Controller;

import com.example.lab5question3.Api.ApiResponse;
import com.example.lab5question3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@RestController
@RequestMapping("event/v1")
public class eventController {
    ArrayList<Event> events = new ArrayList<>();

    //READ Methods
    @GetMapping("/displayAll")
    public ArrayList<Event> displayAll(){
        return events;
    }

    @GetMapping("/searchID/{id}")
    public Event serachID(@PathVariable String id){
        for (Event event: events){
            if(event.getID().equalsIgnoreCase(id)){
                return event;
            }
        }
        return null;
    }

    //CREATE Methods
    @PostMapping("create")
    public ApiResponse create (@RequestBody Event event){
        events.add(event);
        return new ApiResponse("Event created successfully");
    }

    //UPDATE Methods
    @PutMapping("/update/{index}")
    public ApiResponse update (@PathVariable int index, @RequestBody Event event){
        events.set(0,event);
        return new ApiResponse("event updated successfully");
    }

    @PutMapping("/newCapacity/{index}/{newCapacity}")
    public ApiResponse updateCapacity(@PathVariable int index ,@PathVariable int newCapacity){
        events.get(index).setCapacity(newCapacity);
        return new ApiResponse("capacity updated successfully");
    }

    //DELETE Methods
    @DeleteMapping("delete/{index}")
    public ApiResponse delete(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("event deleted successfully");
    }

    //Extra Endpoints
    @GetMapping("/period/{index}")
    public ApiResponse period(@PathVariable int index){
        int year = (int)events.get(index).getStartDate().getYear();
        int month = (int)events.get(index).getStartDate().getMonthValue();
        int day = (int)events.get(index).getStartDate().getDayOfMonth();

        int year2 = (int)events.get(index).getEndDate().getYear();
        int month2 = (int)events.get(index).getEndDate().getMonthValue();
        int day2 = (int)events.get(index).getEndDate().getDayOfMonth();

        Period result = Period.between(LocalDate.of(year,month,day),LocalDate.of(year2,month2,day2));

        return new ApiResponse("This project will last for " + result.getYears()+ " Years, "+ result.getMonths()+ " Months, "+ result.getDays() + " Days");
    }

    @PutMapping("/extend/{index}")
    public ApiResponse extendPeriod(@PathVariable int index ,@RequestBody LocalDate date){
        events.get(index).setEndDate(date);
        return new ApiResponse("Duration of Event extended Successfully");
    }

}
