package com.example.SpringMySQL.repository;


import com.example.SpringMySQL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
