package com.example.packup.Service;

import com.example.packup.Api.ApiException;
import com.example.packup.Model.Review;
import com.example.packup.Model.Space;
import com.example.packup.Repository.SpaceRepository;
import com.example.packup.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;

    private final UserRepository userRepository;
    //CRUD

    //READ
    public List<Space> getAllSpaces(){
        List<Space> spaces = spaceRepository.findAll();
        if(spaces.isEmpty()){
            throw new ApiException("No Spaces");
        }
        return spaces;
    }

    //CREATE
    public void createSpace(Space space){
        if(userRepository.findUserById(space.getOwnerId()) == null){
            throw new ApiException("No Space Owner With This ID !");
        }
        spaceRepository.save(space);
    }

    //UPDATE
    public void updateSpace(Integer id, Space space) {
        Space space1 = spaceRepository.findSpaceById(id);
        if (space1 == null) {
            throw new ApiException("No Space With This ID !"); //checking if there's a space with this id
        }

        if(userRepository.findUserById(space.getOwnerId()) == null){
            throw new ApiException("No Space Owner With This ID !"); //checking if there's a space owner with this id
        }
        if(!spaceRepository.findSpaceById(id).getOwnerId().equals(space.getOwnerId())){
            throw new ApiException("You are not the owner of this space");
        }

        space1.setOwnerId(space.getOwnerId());
        space1.setTitle(space.getTitle());
        space1.setDescription(space.getDescription());
        space1.setCity(space.getCity());
        space1.setAddress(space.getAddress());
        space1.setPricePerDay(space.getPricePerDay());
        space1.setSpaceSize(space.getSpaceSize());
        space1.setStatus(space.getStatus());
        spaceRepository.save(space1);
    }

    //DELETE
    public void deleteSpace(Integer id){
        Space space= spaceRepository.findSpaceById(id);
        if(space == null){
            throw new ApiException("No space with this Id");
        }
        spaceRepository.delete(space);
    }

    //get All owner spaces (2/15)
    // used both by SpaceController's endpoint and internally by BookingService,
    // so it only checks the owner exists — it never fails just because the
    // list happens to be empty, that call-specific check stays in SpaceController.
    public List<Space> ownerSpaces(Integer ownerId){
        if(userRepository.findUserById(ownerId)==null){
            throw new ApiException("No user with this Id");
        }
        List<Space> spaces = spaceRepository.findSpaceByOwnerId(ownerId);
        return spaces;
    }

    //search for space by city status = AVAILABLE (3/15)
    public List<Space> citySpaces(String city){
        List<Space> citySpaces = spaceRepository.availableSpacesInCity(city);
        if(citySpaces.isEmpty()){
            throw new ApiException("No spaces in this location");
        }
        return citySpaces;
    }

    //search for space within certain price range (4/15)
    public List<Space> priceRangeSpaces(Double budget){
        List<Space> budgetSpaces = spaceRepository.spacesWithinBudget(budget);
        if(budgetSpaces.isEmpty()){
            throw new ApiException("No spaces within this range");
        }
        return budgetSpaces;
    }

    //changing the status of the space 6/15
    public void changeSpaceStatus(Integer spaceId, Integer ownerId){
        Space space= spaceRepository.findSpaceById(spaceId);
        if(space == null){
            throw new ApiException("No space with this id");
        }
        if(userRepository.findUserById(ownerId) == null){
            throw new ApiException("No owner with this id");
        }
        if(spaceRepository.findSpaceByOwnerId(ownerId) == null){
            throw new ApiException(" owner doesn't own any spaces yet");
        }
        if( !(space.getOwnerId().equals(ownerId)) ){
            throw new ApiException("owner doesn't own the space");
        }

        if(space.getStatus().equalsIgnoreCase("available"))
            space.setStatus("UNAVAILABLE");
        else {
            space.setStatus("AVAILABLE");
        }
        spaceRepository.save(space);
    }



}
