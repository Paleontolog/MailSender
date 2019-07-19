package com.mailsender.demo.service;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.database.dto.AddresseesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseAccessor databaseAccessor;

    @Autowired
    public DatabaseService(DatabaseAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    public List<AddresseesDB> getListOfAddresses() {
        return databaseAccessor.getAllAddresses();
    }

    public int addAddresses(AddresseesDB addresseesDB) {
        return databaseAccessor.addAddressees(addresseesDB);
    }

    public int updateAddresses(AddresseesDB addresseesDB) {
        return databaseAccessor.updateAddresses(addresseesDB);
    }
}
