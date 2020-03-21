package com.mailsender.demo.registration.repository;

import com.mailsender.demo.registration.model.User;

import java.util.List;

public interface UserRepository {
    User findUserByEmail(String email);
    void saveUser(User user);

    List<User> getAllUsers();
}
