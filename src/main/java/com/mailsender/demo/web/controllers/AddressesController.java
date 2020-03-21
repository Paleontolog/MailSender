package com.mailsender.demo.web.controllers;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.AddresseesService;
import com.mailsender.demo.web.dto.AddressesWebDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
    @Autowired
    private AddresseesService addresseesService;
    @Autowired
    private Converter converter;

    @GetMapping
    public ResponseEntity<List<AddressesWebDTO>> getListOfAddressees(Principal principal) {
        return new ResponseEntity<>(addresseesService.getListOfAddresses(principal)
                .stream().map((ad)->converter.databaseToWebAddressees(ad))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> add (@RequestBody AddressesWebDTO addressees, Principal principal) {
        log.info(addressees.toString());
        AddresseesDB addresseesDB = converter.webAddressesToDatabase(addressees);
        addresseesService.addAddresses(addresseesDB, principal);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody AddressesWebDTO addressees, Principal principal) {
        log.info(addressees.toString());
        AddresseesDB addresseesDB = converter.webAddressesToDatabase(addressees);
        addresseesService.updateAddresses(addresseesDB, principal);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AddressesWebDTO>> getAddresseesOnMessage(@PathVariable("id") Long id,
        Principal principal) {
        return new ResponseEntity<>(addresseesService.getAddresseesOnMessage(id, principal)
                .stream().map((add)->converter.databaseToWebAddressees(add))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/email/{mail}")
    public ResponseEntity<List<AddressesWebDTO>> getAddresseesOnMessage(@PathVariable("mail") String email,
                                                                        Principal principal) {
        log.info(email);
        if (email == null || email.equals("") || email.equals(" ")) {
            email = "default";
        }
        return new ResponseEntity<>(addresseesService.getAddresseesByEmail(email, principal)
                .stream().map((add)->converter.databaseToWebAddressees(add))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
