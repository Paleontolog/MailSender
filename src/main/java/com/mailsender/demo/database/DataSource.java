//package com.mailsender.demo.database;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//public class DataSource {
//    @Bean
//    javax.sql.DataSource datasource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUsername("embedded");
//        dataSource.setPassword("embedded");
//        dataSource.setUrl("jdbc:h2:~/example/EmbeddedDb;INIT=create schema if not exists Queue" +
//                "\\; runscript from '~/example/create-db.sql'");
//        return dataSource;
//    }
//}
