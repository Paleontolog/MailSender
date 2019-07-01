package com.mailsender.demo.web;

import com.mailsender.demo.model.Addressees;
import com.mailsender.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
    @Autowired
    public DatabaseService databaseService;

    @GetMapping("/addresses")
    public List<Addressees> getListOfAddressees(){
        return databaseService.getListOfEmails();
    }
}