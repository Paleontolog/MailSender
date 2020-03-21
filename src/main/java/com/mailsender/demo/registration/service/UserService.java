package com.mailsender.demo.registration.service;

import com.mailsender.demo.registration.model.User;

public interface UserService {
    User findUserByEmail (String email);
    void saveUser(User user);
}
