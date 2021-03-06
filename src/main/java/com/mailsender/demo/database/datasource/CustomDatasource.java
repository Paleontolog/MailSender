package com.mailsender.demo.database.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class CustomDatasource {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:./database/EmbeddedDb;" +
                "INIT=create schema if not exists ADDRESSEES" +
                "\\; runscript from 'classpath:/database/create-db.sql'");
//
//        dataSource.setUrl("jdbc:h2:./database/EmbeddedDb;" +
//                "INIT=create schema if not exists ADDRESSEES" +
//                "\\; runscript from './database/create-db.sql'");
        return dataSource;
    }

    @Bean
    public JdbcTemplate createJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }
}
