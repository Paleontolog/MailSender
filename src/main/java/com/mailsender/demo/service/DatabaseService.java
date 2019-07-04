package com.mailsender.demo.service;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.model.Addressees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseAccessor databaseAccessor;

    public DatabaseService(DatabaseAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    public List<Addressees> getListOfEmails() {
        return databaseAccessor.getAllEmails();
    }

    public void addAddresses(String address) {
        databaseAccessor.addAddressees(address);
    }
}
