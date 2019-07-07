//package com.mailsender.demo.database;
//
//import com.mailsender.demo.model.Addressees;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DatabaseAccessor implements IDatabaseAccessor {
//
//    @Value("${spring.datasource.driverClassName}")
//    private String JDBC_driver;// = "org.h2.Driver";
//    @Value("${spring.datasource.url}")
//    private String DB_URL;//= "jdbc:h2:tcp://localhost/~/test";
//    @Value("${spring.datasource.username}")
//    private String USER;// = "sa";
//    @Value("${spring.datasource.password}")
//    private String PASS;// = "";
//
//    @Override
//    public List<Addressees> getAllAddresses() {
//        String sql = "select id, email from ADDRESSEES";
//        List<Addressees> res = new ArrayList<>();
//        try {
//            Class.forName(JDBC_driver);
//            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery(sql)
//            ) {
//                while (rs.next()) {
//                    res.add(new Addressees(rs.getLong("id"),
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
//    public void addAddressees(Addressees addressees) {
//        String sql = "insert into ADDRESSEES values (null, '"+ addressees.getEmail() +"')";
//        execute(sql);
//    }
//
//    @Override
//    public void updateAddresses(Addressees addresses) {
//        String sql = "UPDATE ADDRESSEES SET email ='" + addresses.getEmail() +
//                    "'WHERE ID=" + addresses.getId();
//        execute(sql);
//    }
//
//    private void execute(String sql) {
//        try {
//            Class.forName(JDBC_driver);
//            try(Connection conn = DriverManager.getConnection (DB_URL, USER, PASS);
//                Statement st = conn.createStatement()
//            ) {
//                st.executeUpdate(sql);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//    }
//}
