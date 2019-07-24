package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionsHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public int addAddressees(AddresseesDB addresseesDB) {
        return jdbcTemplate.update("insert into ADDRESSEES values (?,?) ", null, addresseesDB.getEmail());
    }

    @Override
    public int updateAddresses(AddresseesDB addresseesDB) {
        int res = jdbcTemplate.update("UPDATE ADDRESSEES SET email = ? WHERE ID = ?",
                addresseesDB.getEmail(), addresseesDB.getId());
        if(res != 1) {
            log.error("User not found");
            throw new DatabaseException(DatabaseExceptionsHandlers.USER_NOT_FOUND);
        }
        return res;
    }
}
