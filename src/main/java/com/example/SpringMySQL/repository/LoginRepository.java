package com.example.SpringMySQL.repository;

import com.example.SpringMySQL.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    //    @Query("select l from Login l where l.username = ?1 and l.password = ?2")
    Login findByUsernameAndPassword(String username, String password);

    Login findByToken(String token);
}
