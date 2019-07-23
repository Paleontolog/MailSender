package com.mailsender.demo.web;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private Converter converter;

    @GetMapping
    public  ResponseEntity<?> getListOfAddressees() {
        return new ResponseEntity<>(databaseService.getListOfAddresses()
                .stream().map((ad)->converter.databaseToWebAddressees(ad))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> add (@RequestBody AddressesWebDTO addressees) {
        log.info(addressees.toString());
        AddresseesDB addresseesDB = converter.webAddressesToDatabase(addressees);
        databaseService.addAddresses(addresseesDB);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody AddressesWebDTO addressees) {
        log.info(addressees.toString());
        AddresseesDB addresseesDB = converter.webAddressesToDatabase(addressees);
        databaseService.updateAddresses(addresseesDB);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }
}
