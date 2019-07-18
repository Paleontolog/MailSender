package com.mailsender.demo.model;

import com.mailsender.demo.database.dto.AddresseesDB;
import org.junit.Assert;
import org.junit.Test;


public class TestAddressees {
    @Test
    public void test1() {
        AddresseesDB addresseesDB = new AddresseesDB(666L, "Horus@heresy.ru");
        Assert.assertEquals(addresseesDB.getId(), Long.valueOf(666));
        Assert.assertEquals(addresseesDB.getEmail(), "Horus@heresy.ru");
    }
}