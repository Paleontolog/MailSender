package com.mailsender.demo.web;

import com.mailsender.demo.model.Addressees;
import com.mailsender.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( maxAge = 3600)
@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
    @Autowired
    public DatabaseService databaseService;

    @GetMapping
    public  ResponseEntity<?> getListOfAddressees() {
        return new ResponseEntity<>(databaseService.getListOfAddresses(),  HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> add (@RequestBody Addressees addressees) {
        System.out.println(addressees.getEmail());
        databaseService.addAddresses(addressees);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody Addressees addressees) {
        databaseService.updateAddresses(addressees);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }
}
