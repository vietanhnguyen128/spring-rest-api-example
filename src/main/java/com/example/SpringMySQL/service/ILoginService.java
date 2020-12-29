package com.example.SpringMySQL.service;

import com.example.SpringMySQL.model.Login;

public interface ILoginService {
    Login findByUsernameAndPassword(String username, String password);
    Login findByToken(String token);
}
