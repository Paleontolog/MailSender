package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionCode;
import com.mailsender.demo.registration.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class AddresseesRepositoryJDBC implements AddresseesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AddresseesDB> getAllAddresses(Long user) {
        return jdbcTemplate.query("select id, email from ADDRESSEES where ID_USER = ?",
                new Object[] {user},
                (resultSet, i) -> {
                    Long id = resultSet.getLong("id");
                    String email = resultSet.getString("email");
                    return new AddresseesDB(id, email);
                });
    }

    @Override
    public List<AddresseesDB> getAddresseesByEmail(String email, Long user) {
        return jdbcTemplate.query("select id, email from ADDRESSEES " +
                        "where ID_USER = ? and EMAIL LIKE ?",
                new Object[]{user, email + "%"},
                new BeanPropertyRowMapper<>(AddresseesDB.class));
    }

    @Override
    public int addAddressees(AddresseesDB addresseesDB, Long user) {
        return jdbcTemplate.update("insert into ADDRESSEES values (?,?,?)",
                null, addresseesDB.getEmail(), user);
    }

    @Override
    public int updateAddresses(AddresseesDB addresseesDB, Long user) {
        int res = jdbcTemplate.update("UPDATE ADDRESSEES SET email = ? WHERE ID_USER = ? and ID = ?",
                addresseesDB.getEmail(), user, addresseesDB.getId());
        if (res != 1) {
            log.error("User not found");
            throw new DatabaseException(DatabaseExceptionCode.USER_NOT_FOUND);
        }
        return res;
    }

    @Override
    public List<AddresseesDB> getAddressesOnMessage(Long id, Long user) {
        String sql = "select addr.id, addr.email from " +
                "ADDRESSEES addr left join SEND_TO send on addr.id = send.ID_ADDRESSEES " +
                "where addr.ID_USER = ? and send.ID_MESSAGE = ?";
        return jdbcTemplate.query(sql, new Object[]{user, id},
                (resultSet, i) -> {
                    Long idAddr = resultSet.getLong("id");
                    String email = resultSet.getString("email");
                    return new AddresseesDB(idAddr, email);
                });
    }
}
