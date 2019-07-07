package com.mailsender.demo.service;

import com.mailsender.demo.database.IDatabaseAccessor;
import com.mailsender.demo.model.Addressees;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final IDatabaseAccessor databaseAccessor;

    public DatabaseService(IDatabaseAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    public List<Addressees> getListOfAddresses() {
        return databaseAccessor.getAllAddresses();
    }

    public void addAddresses(Addressees addressees) {
        databaseAccessor.addAddressees(addressees);
    }

    public void updateAddresses(Addressees addressees) {
        databaseAccessor.updateAddresses(addressees);
    }
}
