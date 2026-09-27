package com.example.packup.Service;

import com.example.packup.Model.Booking;
import com.example.packup.Model.Space;
import com.example.packup.Model.User;
import com.example.packup.Repository.BookingRepository;
import com.example.packup.Repository.SpaceRepository;
import com.example.packup.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final SpaceRepository spaceRepository;
    private final BookingRepository bookingRepository;

    //CRUD

    //READ
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //CREATE
    public boolean createUser(User user){
        if(userRepository.findUserByPhone(user.getPhone())!=null)
            return false;
        userRepository.save(user);
        return true;
    }

    //UPDATE
    public int updateUser(Integer id, User user) {
        User user1 = userRepository.findUserById(id);
        if (user1 == null) {
            return -1;
        }
        if(!user1.getPhone().equals(user.getPhone()) && userRepository.findUserByPhone(user.getPhone()) != null){
            return -2;
        }
        if(!user1.getPhone().equalsIgnoreCase(user.getPhone()))
            user1.setPhone(user.getPhone());

        user1.setPassword(user.getPassword());
        user1.setFullName(user.getFullName());
        userRepository.save(user1);
        return 0;
    }

    //DELETE
    public boolean deleteUser(Integer id){
        User user= userRepository.findUserById(id);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    //login
    public int loginUser(String phone, String password){
        if(userRepository.findUserByPhone(phone) == null){
            return -1;
        }
        if(! (userRepository.findUserByPhone(phone).getPassword().equalsIgnoreCase(password)) ){
            return -2;
        }
        return 0;
    }

    //block another renter (15/15)
    private static List<String> blockedList = new ArrayList<>();

    public int blockRenter(Integer ownerId, Integer renterId){
        if(userRepository.findUserById(ownerId) == null){
            return -1; //no owner with this id
        }
        if(userRepository.findUserById(renterId) == null){
            return -2; //no renter with this id
        }
        String entry = ownerId + "_" + renterId;
        if(blockedList.contains(entry)){
            return -3; //already blocked
        }
        List<Space> ownerSpaces = spaceRepository.findSpaceByOwnerId(ownerId);
        for(Space space : ownerSpaces){
            List<Booking> activeBookings = bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "BOOKED");
            activeBookings.addAll(bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "ONGOING"));
            for(Booking booking : activeBookings){
                if(booking.getRenterId().equals(renterId)){
                    return -4; //can't block, there's an active booking with this renter
                }
            }
        }
        blockedList.add(entry);
        return 0;
    }

    public boolean isBlocked(Integer ownerId, Integer renterId){
        return blockedList.contains(ownerId + "_" + renterId);
    }

    public List<Integer> getBlockedRenters(Integer ownerId){
        if(userRepository.findUserById(ownerId) == null){
            return null; //no owner with this id
        }
        List<Integer> renters = new ArrayList<>();
        for(String entry : blockedList){
            String[] parts = entry.split("_");
            if(parts[0].equals(String.valueOf(ownerId))){
                renters.add(Integer.parseInt(parts[1]));
            }
        }
        return renters;
    }





}
