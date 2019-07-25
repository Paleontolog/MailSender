package com.mailsender.demo.testdao;

import com.mailsender.demo.database.DatabaseAccessor;
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
@SpringBootTest(classes = DatabaseAccessorTest.class)
@TestConfiguration
public class DatabaseAccessorTest {

    @Autowired
    private DatabaseAccessor databaseAccessor;

    private List<String> emails = Arrays.asList("lezgyan@yandex.ru",
            "lezgyan.artem@yandex.ru");

    @Bean("dataSource")
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
        List<AddresseesDB> loadedAddresses = databaseAccessor.getAllAddresses();
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
    config = @SqlConfig(dataSource = "dataSource"))
    public void getAllTest() {
        checkList(this.emails);
    }

    @Test
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "dataSource"))
    public void addTest() {
        checkList(emails);
        AddresseesDB addresseesDB = new AddresseesDB(3L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
            databaseAccessor.addAddressees(addresseesDB);
        List<String> emailsExtended = new ArrayList<>(emails);
        emailsExtended.add(addresseesDB.getEmail());
        checkList(emailsExtended);
    }

    @Test
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "dataSource"))
    public void updateTestOk() {
        AddresseesDB addresseesDB = new AddresseesDB(2L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        databaseAccessor.updateAddresses(addresseesDB);
        List<String> emailsExtended = new ArrayList<>();
        emailsExtended.add(emails.get(0));
        emailsExtended.add(addresseesDB.getEmail());

        checkList(emailsExtended);
    }

    @Test(expected = DatabaseException.class)
    @Sql(scripts = {"/databasetest/beforetest.sql"},
            config = @SqlConfig(dataSource = "dataSource"))
    public void updateTestException() {
        AddresseesDB addresseesDB = new AddresseesDB(10L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        databaseAccessor.updateAddresses(addresseesDB);
    }
}
