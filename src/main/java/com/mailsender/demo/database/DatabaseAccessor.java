package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;

import javax.sql.DataSource;
import java.util.List;

public interface DatabaseAccessor {
    List<AddresseesDB> getAllAddresses();
    int addAddressees(AddresseesDB addresseesDB);
    int updateAddresses(AddresseesDB addresseesDB);
    void setDataSource(DataSource datasource);
}
