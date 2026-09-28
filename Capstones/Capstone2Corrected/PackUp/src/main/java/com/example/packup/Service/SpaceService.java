package com.example.packup.Service;

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
        return spaceRepository.findAll();
    }

    //CREATE
    public boolean createSpace(Space space){
        if(userRepository.findUserById(space.getOwnerId()) == null){
            return false;
        }
        spaceRepository.save(space);
        return true;
    }

    //UPDATE
    public int updateSpace(Integer id, Space space) {
        Space space1 = spaceRepository.findSpaceById(id);
        if (space1 == null) {
            return -1; //checking if there's a space with this id
        }

        if(userRepository.findUserById(space.getOwnerId()) == null){
            return -2; //checking if there's a space owner with this id
        }
        if(!spaceRepository.findSpaceById(id).getOwnerId().equals(space.getOwnerId())){
            return -3;
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
        return 0;
    }

    //DELETE
    public boolean deleteSpace(Integer id){
        Space space= spaceRepository.findSpaceById(id);
        if(space == null){
            return false;
        }
        spaceRepository.delete(space);
        return true;
    }

    //get All owner spaces (2/15)
    public List<Space> ownerSpaces(Integer ownerId){
        List<Space> spaces = spaceRepository.findSpaceByOwnerId(ownerId);
        if(userRepository.findUserById(ownerId)==null){
            return null;
        }
        return spaces;
    }

    //search for space by city status = AVAILABLE (3/15)
    public List<Space> citySpaces(String city){
        List<Space> citySpaces = spaceRepository.availableSpacesInCity(city);
        return citySpaces;
    }

    //search for space within certain price range (4/15)
    public List<Space> priceRangeSpaces(Double budget){
        List<Space> budgetSpaces = spaceRepository.spacesWithinBudget(budget);
        return budgetSpaces;
    }

    //changing the status of the space 6/15
    public int changeSpaceStatus(Integer spaceId, Integer ownerId){
        Space space= spaceRepository.findSpaceById(spaceId);
        if(space == null){
            return -1;
        }
        if(userRepository.findUserById(ownerId) == null){
            return -2;
        }
        if(spaceRepository.findSpaceByOwnerId(ownerId) == null){
            return -3;
        }
        if( !(space.getOwnerId().equals(ownerId)) ){
            return -4;
        }

        if(space.getStatus().equalsIgnoreCase("available"))
            space.setStatus("UNAVAILABLE");
        else {
            space.setStatus("AVAILABLE");
        }
        spaceRepository.save(space);
        return 0;
    }



}
