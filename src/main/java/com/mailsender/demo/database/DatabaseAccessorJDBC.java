package com.mailsender.demo.database;

import com.mailsender.demo.model.Addressees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DatabaseAccessorJDBC implements IDatabaseAccessor {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Addressees mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String email = rs.getString("email");
        return new Addressees(id, email);
    }


    @Override
    public List<Addressees> getAllAddresses() {
        return jdbcTemplate.query("select id, email from ADDRESSEES",
                (resultSet, i) -> {
                    Long id = resultSet.getLong("id");
                    String email = resultSet.getString("email");
                    return new Addressees(id, email);
                });
    }

    @Override
    public void addAddressees(Addressees addressees) {
        jdbcTemplate.update("insert into ADDRESSEES values (?,?) ", null, addressees.getEmail());
    }

    @Override
    public void updateAddresses(Addressees addressees) {
        jdbcTemplate.update("UPDATE ADDRESSEES SET email = ? WHERE ID = ?",
                addressees.getEmail(), addressees.getId());
    }
}
