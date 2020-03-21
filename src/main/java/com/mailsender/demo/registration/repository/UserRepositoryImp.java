package com.mailsender.demo.registration.repository;

import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionCode;
import com.mailsender.demo.registration.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImp implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE EMAIL LIKE ?",
                    new Object[]{email},
                    new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            log.error(DatabaseExceptionCode.USER_NOT_FOUND.getMessage());
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.update("INSERT INTO USERS(id, email, password, uuid, role)" +
                        "VALUES (?,?,?,?,?)", null, user.getEmail(), user.getPassword(),
                user.getUUID(), user.getRole().getRole());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("Select * from Users", new BeanPropertyRowMapper<>(User.class));
    }
}
