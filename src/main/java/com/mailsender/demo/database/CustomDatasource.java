package com.mailsender.demo.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class CustomDatasource {

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:./src/main/resources/database/EmbeddedDb;INIT=create schema if not exists ADDRESSEES" +
                "\\; runscript from './src/main/resources/database/create-db.sql'");
        return dataSource;
    }


}
