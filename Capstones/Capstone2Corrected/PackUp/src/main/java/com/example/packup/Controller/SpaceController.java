package com.example.packup.Controller;

import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Space;
import com.example.packup.Service.AIDescriptionService;
import com.example.packup.Service.SpaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/space")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    private final AIDescriptionService aiDescriptionService;

    //CRUD
    @GetMapping("/allSpaces")
    public ResponseEntity<?> getAllSpaces (){
        if(spaceService.getAllSpaces().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Spaces"));
        }
        return ResponseEntity.status(200).body(spaceService.getAllSpaces());
    }

    @PostMapping("/createSpace")
    public ResponseEntity<?> createSpace(@RequestBody @Valid Space space, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if(spaceService.createSpace(space)){
            return ResponseEntity.status(200).body(new ApiResponse("new space posted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Space Owner With This ID !"));
    }

    @PutMapping("/updateSpace/{id}")
    public ResponseEntity<?> updateSpace(@PathVariable Integer id, @RequestBody @Valid Space space, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int updated = spaceService.updateSpace(id,space);

        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Space With This ID !"));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No Space Owner With This ID !"));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("You are not the owner of this space"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("space information updated successfully"));

    }

    @DeleteMapping("/deleteSpace/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        if(spaceService.deleteSpace(id)){
            return ResponseEntity.status(200).body(new ApiResponse("space deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No space with this Id"));
    }

    @GetMapping("/ownerSpaces/{ownerId}")
    public ResponseEntity<?> ownerSpaces(@PathVariable Integer ownerId){
        List<Space> spaces = spaceService.ownerSpaces(ownerId);
        if(spaces == null) {
            return ResponseEntity.status(400).body(new ApiResponse("No user with this Id"));
        }
        if(spaces.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No spaces for this user"));
        }
        return ResponseEntity.status(200).body(spaces);
    }

    @GetMapping("/citySpace/{city}")
    public ResponseEntity<?> citySpaces(@PathVariable String city){
        List<Space> spaces= spaceService.citySpaces(city);
        if(spaces.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No spaces in this location"));
        }
        return ResponseEntity.status(200).body(spaces);
    }

    @GetMapping("/budgetSpace/{budget}")
    public ResponseEntity<?> priceRangeSpaces(@PathVariable Double budget){
        List<Space> spaces= spaceService.priceRangeSpaces(budget);
        if(spaces.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No spaces within this range"));
        }
        return ResponseEntity.status(200).body(spaces);
    }


    @PutMapping("/changeStatus/{spaceId}/{ownerId}")
    public ResponseEntity<?> changeSpaceStatus(@PathVariable Integer spaceId, @PathVariable Integer ownerId){
        int updated = spaceService.changeSpaceStatus(spaceId,ownerId);

        if(updated==-1){
            return ResponseEntity.status(400).body(new ApiResponse("No space with this id"));
        }
        if(updated==-2){
            return ResponseEntity.status(400).body(new ApiResponse("No owner with this id"));
        }
        if(updated==-3){
            return ResponseEntity.status(400).body(new ApiResponse(" owner doesn't own any spaces yet"));
        }
        if(updated==-4){
            return ResponseEntity.status(400).body(new ApiResponse("owner doesn't own the space"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("space status updated successfully"));
    }


    @PostMapping("/generateDescription")
    public ResponseEntity<?> generateDescription(@RequestBody Map<String, Object> body){
        String title = (String) body.get("title");
        String city = (String) body.get("city");
        String address = (String) body.get("address");
        Double pricePerDay = body.get("pricePerDay") != null ? Double.valueOf(body.get("pricePerDay").toString()) : null;
        Double spaceSize = body.get("spaceSize") != null ? Double.valueOf(body.get("spaceSize").toString()) : null;

        String description = aiDescriptionService.generateDescription(title, city, address, pricePerDay, spaceSize);
        if(description == null){
            return ResponseEntity.status(500).body(new ApiResponse("Failed to generate description, please try again"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(description));
    }


}
