package com.mailsender.demo.web;

import com.mailsender.demo.service.DatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestController {
    @Mock
    private DatabaseService databaseService;
    @InjectMocks
    private AddressesController addressesController;

    @Test
    public void test() {

    }
}
