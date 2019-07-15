package com.mailsender.demo.model;

import org.junit.Assert;
import org.junit.Test;


public class TestAddressees {
    @Test
    public void test1() {
        Addressees addressees = new Addressees(666L, "Horus@heresy.ru");
        Assert.assertEquals(addressees.getId(), Long.valueOf(666));
        Assert.assertEquals(addressees.getEmail(), "Horus@heresy.ru");
    }
}