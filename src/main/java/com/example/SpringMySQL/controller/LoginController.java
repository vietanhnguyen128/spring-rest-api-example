package com.example.SpringMySQL.controller;

import com.example.SpringMySQL.model.Login;
import com.example.SpringMySQL.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("demo")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {

        Optional<Login> retrievedLogin = Optional.ofNullable(loginService.findByUsernameAndPassword(login.getUsername(), login.getPassword()));

        if (retrievedLogin.isPresent()) {
            Login loginData = retrievedLogin.get();
            String md5Hex = DigestUtils.md5DigestAsHex((loginData.getUsername() + loginData.getPassword()).getBytes()).toUpperCase(); //encrypt string to a md5 hash.
            loginData.setToken(md5Hex);
            loginService.updateLogin(loginData);
            return new ResponseEntity<>("Logged in! Token: " + md5Hex, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

