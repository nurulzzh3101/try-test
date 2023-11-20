package com.example.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findUserByEmail(String email);
    User findUserByStatus(String status);
    User findUserById(String id);
}
