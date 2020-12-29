package com.example.SpringMySQL.controller;

import com.example.SpringMySQL.model.User;
import com.example.SpringMySQL.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class MainController {

    @Autowired
    private MainServiceImpl mainService;

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser (@RequestBody User user) {
        mainService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userData = mainService.findAll();

        if (userData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        Optional<User> userData = mainService.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        Optional<User> userData = mainService.findById(id);

        if (userData.isPresent()) {
            User userInfo = userData.get();

            userInfo.setName(user.getName());
            userInfo.setEmail(user.getEmail());
            userInfo.setAge(user.getAge());
            userInfo.setGender(user.getGender());
            userInfo.setDescription(user.getDescription());

            mainService.updateUser(userInfo);

            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
        if (((Optional<User>) mainService.findById(id)).isPresent()) {
            mainService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
