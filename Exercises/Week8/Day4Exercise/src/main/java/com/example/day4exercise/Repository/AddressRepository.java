package com.example.day4exercise.Repository;

import com.example.day4exercise.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer > {
    Address findAddressById(Integer id);
}
