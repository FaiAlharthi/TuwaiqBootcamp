package com.example.packup.Controller;

import com.example.packup.Api.ApiException;
import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Space;
import com.example.packup.Service.AIDescriptionService;
import com.example.packup.Service.SpaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(200).body(spaceService.getAllSpaces());
    }

    @PostMapping("/createSpace")
    public ResponseEntity<?> createSpace(@RequestBody @Valid Space space){
        spaceService.createSpace(space);
        return ResponseEntity.status(200).body(new ApiResponse("new space posted successfully"));
    }

    @PutMapping("/updateSpace/{id}")
    public ResponseEntity<?> updateSpace(@PathVariable Integer id, @RequestBody @Valid Space space){
        spaceService.updateSpace(id,space);
        return ResponseEntity.status(200).body(new ApiResponse("space information updated successfully"));

    }

    @DeleteMapping("/deleteSpace/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        spaceService.deleteSpace(id);
        return ResponseEntity.status(200).body(new ApiResponse("space deleted successfully"));
    }

    @GetMapping("/ownerSpaces/{ownerId}")
    public ResponseEntity<?> ownerSpaces(@PathVariable Integer ownerId){
        List<Space> spaces = spaceService.ownerSpaces(ownerId);
        if(spaces.isEmpty()){
            throw new ApiException("No spaces for this user");
        }
        return ResponseEntity.status(200).body(spaces);
    }

    @GetMapping("/citySpace/{city}")
    public ResponseEntity<?> citySpaces(@PathVariable String city){
        List<Space> spaces= spaceService.citySpaces(city);
        return ResponseEntity.status(200).body(spaces);
    }

    @GetMapping("/budgetSpace/{budget}")
    public ResponseEntity<?> priceRangeSpaces(@PathVariable Double budget){
        List<Space> spaces= spaceService.priceRangeSpaces(budget);
        return ResponseEntity.status(200).body(spaces);
    }


    @PutMapping("/changeStatus/{spaceId}/{ownerId}")
    public ResponseEntity<?> changeSpaceStatus(@PathVariable Integer spaceId, @PathVariable Integer ownerId){
        spaceService.changeSpaceStatus(spaceId,ownerId);
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
        return ResponseEntity.status(200).body(new ApiResponse(description));
    }


}
