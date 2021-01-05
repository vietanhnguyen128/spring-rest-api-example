package com.example.SpringMySQL.service;

import com.example.SpringMySQL.model.Login;
import lombok.extern.java.Log;

public interface ILoginService {
    public abstract Login findByUsernameAndPassword(String username, String password);
    public abstract Login findByToken(String token);
    public abstract Login updateLogin(Login login);
}
