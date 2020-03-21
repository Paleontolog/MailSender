package com.mailsender.demo.testdao;

import com.mailsender.demo.database.AddresseesRepository;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@TestConfiguration
@Configuration
@SpringBootTest(classes = AddresseesRepositoryTest.class)
public class AddresseesRepositoryTest {

    private static final Long USER = 1L;

    @Autowired
    private AddresseesRepository addresseesRepository;

    private static final List<String> emails = Arrays.asList("lezgyan@yandex.ru",
            "lezgyan.artem@yandex.ru");

    @Bean("testDataSource")
    public EmbeddedDatabase setup() {
         return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(setup());
    }

    private void checkList(List<String> emails) {
        List<AddresseesDB> loadedAddresses = addresseesRepository.getAllAddresses(USER);
        log.debug("Loaded Persons: " + loadedAddresses);
        Long ln = 1L;

        for (String email : emails) {
            Assert.assertEquals(ln, loadedAddresses.get(ln.intValue() - 1).getId());
            Assert.assertEquals(email, loadedAddresses.get(ln.intValue() - 1).getEmail());
            ln++;
        }
    }

    @Test
    @Sql(scripts = {"/databasetest/beforetest.sql"},
    config = @SqlConfig(dataSource = "testDataSource"))
    public void getAllTest() {
        checkList(emails);
    }

    @Test
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "testDataSource"))
    public void addTest() {
        checkList(emails);
        AddresseesDB addresseesDB = new AddresseesDB(3L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
            addresseesRepository.addAddressees(addresseesDB, USER);
        List<String> emailsExtended = new ArrayList<>(emails);
        emailsExtended.add(addresseesDB.getEmail());
        checkList(emailsExtended);
    }

    @Test
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "testDataSource"))
    public void updateTestOk() {
        AddresseesDB addresseesDB = new AddresseesDB(2L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        addresseesRepository.updateAddresses(addresseesDB, USER);
        List<String> emailsExtended = new ArrayList<>();
        emailsExtended.add(emails.get(0));
        emailsExtended.add(addresseesDB.getEmail());

        checkList(emailsExtended);
    }

    @Test(expected = DatabaseException.class)
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "testDataSource"))
    public void updateTestException() {
        AddresseesDB addresseesDB = new AddresseesDB(10L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        addresseesRepository.updateAddresses(addresseesDB, USER);
    }
}
