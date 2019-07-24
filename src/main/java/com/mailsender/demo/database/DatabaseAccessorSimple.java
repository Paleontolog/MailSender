//package com.mailsender.demo.database;
//
//import com.mailsender.demo.database.dto.AddresseesDB;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class DatabaseAccessorSimple implements DatabaseAccessor {
//
//    private static final String JDBC_DRIVER = "org.h2.Driver";
//    private static final String DB_URL = "jdbc:h2:./src/main/resources/database/EmbeddedDb;" +
//            "INIT=create schema if not exists ADDRESSEES" +
//            "\\; runscript from './src/main/resources/database/create-db.sql'";
//    private static final String USER = "sa";
//    private static final String PASS = "";
//
//    @Override
//    public List<AddresseesDB> getAllAddresses() {
//        String sql = "select id, email from ADDRESSEES";
//        List<AddresseesDB> res = new ArrayList<>();
//        try {
//            Class.forName(JDBC_DRIVER);
//            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery(sql)
//            ) {
//                while (rs.next()) {
//                    res.add(new AddresseesDB(rs.getLong("id"),
//                            rs.getString("email")));
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
//
//    @Override
//    public int addAddressees(AddresseesDB addressees) {
//        String sql = "insert into ADDRESSEES values (null, '"+ addressees.getEmail() +"')";
//        return execute(sql);
//    }
//
//    @Override
//    public int updateAddresses(AddresseesDB addresses) {
//        String sql = "UPDATE ADDRESSEES SET email ='" + addresses.getEmail() +
//                    "'WHERE ID=" + addresses.getId();
//        return execute(sql);
//    }
//
//    private int execute(String sql) {
//        try {
//            Class.forName(JDBC_DRIVER);
//            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
//                Statement st = conn.createStatement()
//            ) {
//               return st.executeUpdate(sql);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return 0;
//    }
//}
