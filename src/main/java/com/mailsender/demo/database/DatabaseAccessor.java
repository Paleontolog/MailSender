package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;

import java.util.List;

public interface DatabaseAccessor {
    List<AddresseesDB> getAllAddresses();
    int addAddressees(AddresseesDB addresseesDB);
    int updateAddresses(AddresseesDB addresseesDB);
}
