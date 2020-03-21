package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MailRepositoryJDBC implements MailRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveMessage(MessageDB messageDB, Long user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("MESSAGES").usingGeneratedKeyColumns("ID");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("subject", messageDB.getSubject())
                .addValue("email", messageDB.getEmail())
                .addValue("ID_USER", user);
        Number number = simpleJdbcInsert.executeAndReturnKey(params);
        return number.intValue();
    }

    @Override
    public int updateMessage(MessageDB messageDB, Long user) {
        int res = jdbcTemplate.update("UPDATE MESSAGES SET subject = ?, email = ? WHERE ID_USER = ? and  ID = ?",
                messageDB.getSubject(), messageDB.getEmail(), user, messageDB.getId());
        if (res != 1) {
            log.error(DatabaseExceptionCode.MESSAGE_NOT_FOUND.getMessage());
            throw new DatabaseException(DatabaseExceptionCode.USER_NOT_FOUND);
        }
        return res;
    }

    @Override
    public MessageDB getMessageOnID(Long id, Long user) {
        try {
            return jdbcTemplate.queryForObject("select ID, SUBJECT, EMAIL from MESSAGES where ID_USER = ? and ID = ?",
                    new Object[]{user, id}, new BeanPropertyRowMapper<>(MessageDB.class));
        } catch (EmptyResultDataAccessException e) {
            log.error(DatabaseExceptionCode.MESSAGE_NOT_FOUND.getMessage());
            throw new DatabaseException(DatabaseExceptionCode.MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public List<MessageDB> getNMessage(Long N, Long d, Long user) {
        return jdbcTemplate.query("select id, subject, email from MESSAGES where ID_USER = ?",
                new Object[] {user},
                new BeanPropertyRowMapper<>(MessageDB.class));
    }

    @Override
    public List<MessageDB> getAllMessage(Long user) {
        return jdbcTemplate.query("select id, subject, email from MESSAGES where ID_USER = ?",
                new Object[] {user},
                new BeanPropertyRowMapper<>(MessageDB.class));
    }


    @Override
    public int createNewLink(Long idAddressees, Long idMessage) {
        return jdbcTemplate.update("insert into SEND_TO values (?,?,?) ", null, idAddressees, idMessage);
    }

    @Override
    public int deleteMessage(Long id, Long user) {
        String sql = "DELETE FROM MESSAGES WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteLinks(Long messageID, Long user) {
        String sql = "DELETE FROM SEND_TO s where s.ID_MESSAGE = ? and EXISTS (" +
                "SELECT * from MESSAGES where id = ? and ID_USER = ?)";
        return jdbcTemplate.update(sql, messageID, messageID, user);
    }
}
