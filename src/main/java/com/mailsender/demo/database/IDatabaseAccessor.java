package com.mailsender.demo.database;

import com.mailsender.demo.model.Addressees;

import java.util.List;

public interface IDatabaseAccessor {
    List<Addressees> getAllAddresses();
    void addAddressees(Addressees addressees);
    void updateAddresses(Addressees addressees);
}
