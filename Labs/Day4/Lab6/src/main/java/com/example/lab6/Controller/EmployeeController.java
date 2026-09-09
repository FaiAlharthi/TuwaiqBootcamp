package com.example.lab6.Controller;

import com.example.lab6.Api.ApiResponse;
import com.example.lab6.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/employee/v1")

public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    //READ Methods

    @GetMapping("/displayAll")
    public ResponseEntity<?> displayAll(){
        return ResponseEntity.status(200).body(employees);
    }

    @GetMapping("/searchPosition/{position}")
    public ResponseEntity<?> searchPositions (@PathVariable String position ){
        ArrayList<Employee> newEmployees = new ArrayList<>();
        if( ! (position.equalsIgnoreCase("supervisor")) && !(position.equalsIgnoreCase("coordinator"))){
            return ResponseEntity.status(400).body(new ApiResponse("The Position should be ( Coordinator OR Supervisor ) ONLY"));
        }
        for(Employee employee:employees){
            if(employee.getPosition().equalsIgnoreCase(position)){
                newEmployees.add(employee);
            }
        }
        return ResponseEntity.status(200).body(newEmployees);
    }

    @GetMapping("/getByAgeRange/{minAge}/{maxAge}")
    public ResponseEntity<?> getByAgeRange(@PathVariable int minAge, @PathVariable int maxAge){
        ArrayList<Employee> newEmployees = new ArrayList<>();
        if(minAge <= 0 || maxAge <= 0){
            return ResponseEntity.status(400).body(new ApiResponse("Add A valid Range (numbers should be more than 0 )"));
        }

        for(Employee employee: employees){
            if(employee.getAge() >= minAge && employee.getAge() <= maxAge){
                newEmployees.add(employee);
            }
        }
        return ResponseEntity.status(200).body(newEmployees);
    }

    @GetMapping("/NoAnnualLeave")
    public ResponseEntity<?> NoAnnualLeave (){
        ArrayList<Employee> newEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.getAnnualLeave() == 0){
                newEmployees.add(employee);
            }
        }
        return ResponseEntity.status(200).body(newEmployees);
    }

    //---------------------------------------------------------------------------------

    //CREATE Methods

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee added successfully"));
    }

    //---------------------------------------------------------------------------------

    //UPDATE Methods

    @PutMapping("/update/{index}")
    public ResponseEntity<?> update(@PathVariable int index, @RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if( index >= employees.size() || index <0){
            return ResponseEntity.status(400).body(new ApiResponse("employee with this index not exist !"));
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @PutMapping("/applyOL/{index}")
    public ResponseEntity<?> applyAnnualLeave ( @PathVariable int index){
        if( index >= employees.size() || index <0){
            return ResponseEntity.status(400).body(new ApiResponse("employee with this index not exist !"));
        }
        if(employees.get(index).isOnLeave()==true){
            return ResponseEntity.status(400).body(new ApiResponse("Employee already onLeave"));
        }
        if(employees.get(index).getAnnualLeave() <= 0){
            return ResponseEntity.status(400).body(new ApiResponse("Employee has No onLeave days left"));
        }
        employees.get(index).setOnLeave(true);
        employees.get(index).setAnnualLeave( (employees.get(index).getAnnualLeave()-1) );

        return ResponseEntity.status(200).body(new ApiResponse("Employee Application For OnLeave Is Successfully Done "));
    }

    @PutMapping("/promotion/{supIndex}/{empID}")
    public ResponseEntity<?> promoteEmployee (@PathVariable int supIndex, @PathVariable String empID){
        boolean exist= false;
        Employee promotedEmployee = new Employee();

        if( supIndex >= employees.size() || supIndex <0){
            return ResponseEntity.status(400).body(new ApiResponse("No Supervisor With This Index!"));
        }
        if( !(employees.get(supIndex).getPosition().equalsIgnoreCase("supervisor")) ){
            return ResponseEntity.status(403).body(new ApiResponse("You Are Not A Supervisor To Do This Process ! "));
        }

        for(Employee employee : employees){
            if( employee.getID().equalsIgnoreCase(empID) ){
                exist = true;
                promotedEmployee = employee;
            }
        }
        if(!exist){
            return ResponseEntity.status(400).body(new ApiResponse("employee with this ID not exist !"));
        }

        if(promotedEmployee.getAge() < 30){
            return ResponseEntity.status(400).body(new ApiResponse("Age of employee should be more than 30"));
        }

        if(promotedEmployee.isOnLeave()){
            return ResponseEntity.status(400).body(new ApiResponse("employee should not be OnLeave "));
        }

        if(promotedEmployee.getPosition().equalsIgnoreCase("Supervisor")){
            return ResponseEntity.status(400).body(new ApiResponse("employee is a Supervisor already "));
        }

        promotedEmployee.setPosition("supervisor");
        return ResponseEntity.status(200).body(new ApiResponse("Employee Got Promoted"));
    }

    //---------------------------------------------------------------------------------

    //DELETE Methods

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> delete (@PathVariable int index){
        if( index >= employees.size() || index <0){
            return ResponseEntity.status(400).body(new ApiResponse("this index not valid !"));
        }
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("employee deleted successfully !"));
    }

}
