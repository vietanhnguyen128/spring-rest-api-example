package com.example.SpringMySQL.service;

import com.example.SpringMySQL.model.User;
import com.example.SpringMySQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainServiceImpl implements IMainService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        Optional<User> userData = this.findById(id);

        if (userData.isPresent()) {
            User userInfo = userData.get();

            userInfo.setName(user.getName());
            userInfo.setEmail(user.getEmail());
            userInfo.setAge(user.getAge());
            userInfo.setGender(user.getGender());
            userInfo.setDescription(user.getDescription());

            return userRepository.save(userInfo);
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (((Optional<User>) findById(id)).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
