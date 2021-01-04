package com.example.SpringMySQL.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(name = "username", unique = true)
    @Getter @Setter
    private String username;

    @Getter @Setter
    @Column(name = "password")
    private String password;

    @Column(name = "token")
    @Getter @Setter
    private String token;

    public Login() {
    }
}
