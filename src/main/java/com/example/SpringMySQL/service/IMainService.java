package com.example.SpringMySQL.service;

import com.example.SpringMySQL.model.User;

import java.util.List;
import java.util.Optional;

public interface IMainService {
    public abstract User createUser(User user);

    public abstract User updateUser(User user);

    public abstract List<User> findAll();

    public abstract Optional<User> findById(Integer id);

    public abstract void deleteById(Integer id);
}
