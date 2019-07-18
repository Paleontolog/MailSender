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

    public void addAddresses(AddresseesDB addresseesDB) {
        databaseAccessor.addAddressees(addresseesDB);
    }

    public void updateAddresses(AddresseesDB addresseesDB) {
        databaseAccessor.updateAddresses(addresseesDB);
    }
}
