package com.example.packup.Repository;

import com.example.packup.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer Id);
    User findUserByPhone(String phone);
    User findUserByEmail(String email);
}
