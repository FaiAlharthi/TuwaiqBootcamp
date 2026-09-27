package com.example.packup.Repository;

import com.example.packup.Model.Space;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Integer> {
    Space findSpaceById(Integer Id);
    List<Space> findSpaceByOwnerId(Integer Id);

    @Query("select s from Space s where s.city=?1 and s.status='available'")
    List<Space> availableSpacesInCity(String city);

    @Query("select s from Space s where s.pricePerDay <=?1")
    List<Space> spacesWithinBudget(Double budget);

//    @Query("select s from Space s where s.ownerId=?1 And s.Id=?2 ")
//    Space ifExist(Integer ownerId, Integer id);

}
