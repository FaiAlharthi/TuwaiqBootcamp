package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //CRUD
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean addUser(User user){
        userRepository.save(user);
        return true;
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser = userRepository.getUserById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());

        userRepository.save(user);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.getUserById(id);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

}
