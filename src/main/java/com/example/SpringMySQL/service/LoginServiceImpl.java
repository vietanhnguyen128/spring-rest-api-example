package com.example.SpringMySQL.service;

import com.example.SpringMySQL.model.Login;
import com.example.SpringMySQL.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login findByUsernameAndPassword(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Login findByToken(String token) {
        return loginRepository.findByToken(token);
    }

    public Login updateLogin(Login login) {
        return loginRepository.save(login);
    }
}
