package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
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
public class DatabaseAccessorJDBC implements DatabaseAccessor {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AddresseesDB> getAllAddresses() {
        return jdbcTemplate.query("select id, email from ADDRESSEES",
                (resultSet, i) -> {
                    Long id = resultSet.getLong("id");
                    String email = resultSet.getString("email");
                    return new AddresseesDB(id, email);
                });
    }

    @Override
    public List<AddresseesDB> getAddresseesByEmail(String email) {
        return jdbcTemplate.query("select id, email from ADDRESSEES WHERE EMAIL LIKE ?",
                new Object[]{email + "%"}, new BeanPropertyRowMapper<>(AddresseesDB.class));
    }

    @Override
    public int addAddressees(AddresseesDB addresseesDB) {
        return jdbcTemplate.update("insert into ADDRESSEES values (?,?) ", null, addresseesDB.getEmail());
    }

    @Override
    public int updateAddresses(AddresseesDB addresseesDB) {
        int res = jdbcTemplate.update("UPDATE ADDRESSEES SET email = ? WHERE ID = ?",
                addresseesDB.getEmail(), addresseesDB.getId());
        if(res != 1) {
            log.error("User not found");
            throw new DatabaseException(DatabaseExceptionCode.USER_NOT_FOUND);
        }
        return res;
    }

    @Override
    public int saveMessage(MessageDB messageDB) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("MESSAGES").usingGeneratedKeyColumns("ID");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("subject", messageDB.getSubject())
                .addValue("email", messageDB.getEmail());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);
        return number.intValue();
    }

    @Override
    public int updateMessage(MessageDB messageDB) {
        int res = jdbcTemplate.update("UPDATE MESSAGES SET subject = ?, email = ? WHERE ID = ?",
                messageDB.getSubject(), messageDB.getEmail(), messageDB.getId());
        if(res != 1) {
            log.error(DatabaseExceptionCode.MESSAGE_NOT_FOUND.getMessage());
            throw new DatabaseException(DatabaseExceptionCode.USER_NOT_FOUND);
        }
        return res;
    }

    @Override
    public MessageDB getMessageOnID(Long id) {
        try {
            return jdbcTemplate.queryForObject("select ID, SUBJECT, EMAIL from MESSAGES where ID = ?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(MessageDB.class));
        } catch (EmptyResultDataAccessException e) {
            log.error(DatabaseExceptionCode.MESSAGE_NOT_FOUND.getMessage());
            throw new DatabaseException(DatabaseExceptionCode.MESSAGE_NOT_FOUND);
        }
    }

    @Override
    public List<MessageDB> getNMessage(Long N, Long d) {
        return jdbcTemplate.query("select id, subject, email from MESSAGES",
                new BeanPropertyRowMapper<>(MessageDB.class));
    }

    @Override
    public List<MessageDB> getAllMessage() {
        return jdbcTemplate.query("select id, subject, email from MESSAGES",
                new BeanPropertyRowMapper<>(MessageDB.class));
    }


    @Override
    public int createNewLink(Long idAddressees, Long idMessage) {
        return jdbcTemplate.update("insert into SEND_TO values (?,?,?) ", null, idAddressees, idMessage);
    }

    @Override
    public List<AddresseesDB> getAddressesOnMessage(Long id) {
        String sql = "select addr.id, addr.email from \n" +
                "ADDRESSEES addr left join SEND_TO send on addr.id = send.ID_ADDRESSEES\n" +
                "where send.ID_MESSAGE = ?";
        return jdbcTemplate.query(sql, new Object[]{id},
                (resultSet, i) -> {
                    Long idAddr = resultSet.getLong("id");
                    String email = resultSet.getString("email");
                    return new AddresseesDB(idAddr, email);
                });
    }

    @Override
    public int deleteMessage(Long id) {
        String sql = "DELETE FROM MESSAGES WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteLinks(Long messageID) {
        String sql = "DELETE FROM SEND_TO WHERE ID_MESSAGE = ?";
        return jdbcTemplate.update(sql, messageID);
    }
}
