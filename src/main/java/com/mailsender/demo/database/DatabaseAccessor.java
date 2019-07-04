package com.mailsender.demo.database;

import com.mailsender.demo.model.Addressees;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseAccessor {

    private static final String JDBC_driver = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";//"jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";

    public List<Addressees> getAllEmails() {
        String sql = "select id, email from ADDRESSEES";
        List<Addressees> res = new ArrayList<>();
        try {
            Class.forName(JDBC_driver);
            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
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

    public void addAddressees(String email) {
        String sql = "insert into ADDRESSEES values (3, '"+ email +"')";
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
