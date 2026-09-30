package com.example.day4exercise.Service;

import com.example.day4exercise.Api.ApiException;
import com.example.day4exercise.DTO.AddressDTO;
import com.example.day4exercise.Model.Address;
import com.example.day4exercise.Model.Teacher;
import com.example.day4exercise.Repository.AddressRepository;
import com.example.day4exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public void createAddress(AddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if(teacher == null){
            throw new ApiException("No teacher found");
        }
        Address address = new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacherRepository.findTeacherById(addressDTO.getTeacher_id()));
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO){
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if(address == null){
            throw new ApiException("No address found");
        }

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }

    public void deleteAddress(Integer id){
        Address address = addressRepository.findAddressById(id);
        if(address == null){
            throw new ApiException("No address found");
        }
        addressRepository.delete(address);
    }
}
