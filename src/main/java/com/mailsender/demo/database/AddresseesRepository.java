package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;

import javax.sql.DataSource;
import java.util.List;

public interface AddresseesRepository {
    List<AddresseesDB> getAllAddresses(Long user);
    List<AddresseesDB> getAddresseesByEmail(String name, Long user);
    int addAddressees(AddresseesDB addresseesDB, Long user);
    int updateAddresses(AddresseesDB addresseesDB, Long user);
    List<AddresseesDB> getAddressesOnMessage(Long id, Long user);
}
