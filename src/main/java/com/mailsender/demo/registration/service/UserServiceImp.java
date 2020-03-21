package com.mailsender.demo.registration.service;

import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionCode;
import com.mailsender.demo.registration.model.Role;
import com.mailsender.demo.registration.model.User;
import com.mailsender.demo.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImp  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setUUID(UUID.randomUUID().toString());
        userRepository.saveUser(user);
    }
}
