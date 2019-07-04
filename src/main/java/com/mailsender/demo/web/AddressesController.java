package com.mailsender.demo.web;

import com.mailsender.demo.model.Addressees;
import com.mailsender.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<?> add (@RequestBody Addressees addressees) {
        databaseService.addAddresses(addressees.getEmail());
        return new ResponseEntity<>(null,  HttpStatus.CREATED);
    }
}
