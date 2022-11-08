package com.example.bulletinboard.service;

import com.example.bulletinboard.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    List<User> getAllUsers();
    User findUserByLogin(String login);
    User findUserByLoginAndPassword(String login, String password);
}
