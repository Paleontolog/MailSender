package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionsHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class DatabaseAccessorJDBC implements DatabaseAccessor {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private AddresseesDB mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String email = rs.getString("email");
        return new AddresseesDB(id, email);
    }


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
            throw new DatabaseException(DatabaseExceptionsHandlers.USER_NOT_FOUND);
        }
        return res;
    }
}
