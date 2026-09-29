package com.example.packup.Service;

import com.example.packup.Api.ApiException;
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
    public void createUser(User user){
        if(userRepository.findUserByPhone(user.getPhone())!=null)
            throw new ApiException("Phone number already exists");
        if(userRepository.findUserByEmail(user.getEmail())!=null)
            throw new ApiException("Email already exists");
        userRepository.save(user);
    }

    //UPDATE
    public void updateUser(Integer id, User user) {
        User user1 = userRepository.findUserById(id);
        if (user1 == null) {
            throw new ApiException("No user with this Id");
        }
        if(!user1.getPhone().equals(user.getPhone()) && userRepository.findUserByPhone(user.getPhone()) != null){
            throw new ApiException("Phone number already exists bu another user");
        }
        if(!user1.getEmail().equals(user.getEmail()) && userRepository.findUserByEmail(user.getEmail()) != null){
            throw new ApiException("Email already exists with another user");
        }
        if(!user1.getPhone().equalsIgnoreCase(user.getPhone()))
            user1.setPhone(user.getPhone());
        if(!user1.getEmail().equalsIgnoreCase(user.getEmail()))
            user1.setEmail(user.getEmail());

        user1.setPassword(user.getPassword());
        user1.setFullName(user.getFullName());
        userRepository.save(user1);
    }

    //DELETE
    public void deleteUser(Integer id){
        User user= userRepository.findUserById(id);
        if(user == null){
            throw new ApiException("No user with this Id");
        }
        userRepository.delete(user);
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

    public void blockRenter(Integer ownerId, Integer renterId){
        if(userRepository.findUserById(ownerId) == null){
            throw new ApiException("No owner with this id");
        }
        if(userRepository.findUserById(renterId) == null){
            throw new ApiException("No renter with this id");
        }
        String entry = ownerId + "_" + renterId;
        if(blockedList.contains(entry)){
            throw new ApiException("This renter is already blocked");
        }
        List<Space> ownerSpaces = spaceRepository.findSpaceByOwnerId(ownerId);
        for(Space space : ownerSpaces){
            List<Booking> activeBookings = bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "BOOKED");
            activeBookings.addAll(bookingRepository.findBookingBySpaceIdAndStatus(space.getId(), "ONGOING"));
            for(Booking booking : activeBookings){
                if(booking.getRenterId().equals(renterId)){
                    throw new ApiException("Cannot block a renter with active booking (BOOKED or ONGOING)");
                }
            }
        }
        blockedList.add(entry);
    }

    public boolean isBlocked(Integer ownerId, Integer renterId){
        return blockedList.contains(ownerId + "_" + renterId);
    }

    public List<Integer> getBlockedRenters(Integer ownerId){
        if(userRepository.findUserById(ownerId) == null){
            throw new ApiException("No owner with this id");
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
