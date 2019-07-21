package com.mailsender.demo.testdao;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.database.DatabaseAccessorJDBC;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.exceptions.DatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseAccessorTest {
    private DatabaseAccessor databaseAccessor;

    private List<String> emails = Arrays.asList("lezgyan@yandex.ru",
            "lezgyan.artem@yandex.ru");

    private EmbeddedDatabase db;

    @Before
    public void setup() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("databasetest/beforetest.sql")
                .build();
        databaseAccessor = new DatabaseAccessorJDBC();
        databaseAccessor.setDataSource(db);
    }

    @After
    public void reset() {
        db.shutdown();
    }

    private void checkList(List<String> emails) {
        List<AddresseesDB> loadedAddresses = databaseAccessor.getAllAddresses();
        log.debug("Loaded Persons: " + loadedAddresses);
        Long ln = 1L;

        for (AddresseesDB addresseesDB : loadedAddresses) {
            Assert.assertTrue(ln.equals(addresseesDB.getId()));
            Assert.assertTrue(emails.get(ln.intValue() - 1)
                    .equals(addresseesDB.getEmail()));
            ln++;
        }
    }

    @Test
    public void getAllTest() {
        checkList(this.emails);
    }

    @Test
    //@Rollback
    public void addTest() {
        AddresseesDB addresseesDB = new AddresseesDB(3L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        databaseAccessor.addAddressees(addresseesDB);
        List<String> emailsExtended = new ArrayList<>(emails);
        emailsExtended.add(addresseesDB.getEmail());
        checkList(emailsExtended);
    }

    @Test
    //@Rollback
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
    //@Rollback
    public void updateTestException() {
        AddresseesDB addresseesDB = new AddresseesDB(10L, "heretic@horus.ru");
        log.debug("Loaded Persons: " + addresseesDB);
        databaseAccessor.updateAddresses(addresseesDB);
    }

}
