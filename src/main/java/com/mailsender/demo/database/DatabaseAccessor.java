package com.mailsender.demo.database;

import com.mailsender.demo.model.Addressees;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseAccessor implements IDatabaseAccessor{

    private static final String JDBC_driver = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USER = "sa";
    private static final String PASS = "";

    @Override
    public List<Addressees> getAllAddresses() {
        String sql = "select id, email from ADDRESSEES";
        List<Addressees> res = new ArrayList<>();
        try {
            Class.forName(JDBC_driver);
            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
            ) {
                while (rs.next()) {
                    res.add(new Addressees(rs.getLong("id"),
                            rs.getString("email")));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public void addAddressees(Addressees addressees) {
        String sql = "insert into ADDRESSEES values (null, '"+ addressees.getEmail() +"')";
        execute(sql);
    }

    @Override
    public void updateAddresses(Addressees addresses) {
        String sql = "UPDATE ADDRESSEES SET email ='" + addresses.getEmail() +
                    "'WHERE ID=" + addresses.getId();
        execute(sql);
    }

    private void execute(String sql) {
        try {
            Class.forName(JDBC_driver);
            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
                Statement st = conn.createStatement()
            ) {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
