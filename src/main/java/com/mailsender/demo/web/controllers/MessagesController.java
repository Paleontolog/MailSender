package com.mailsender.demo.web.controllers;

import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.MailService;
import com.mailsender.demo.web.dto.AddressesWebDTO;
import com.mailsender.demo.web.dto.MessageAllInfo;
import com.mailsender.demo.web.dto.MessageWebDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
    @RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private MailService mailService;
    @Autowired
    private Converter converter;

    @PutMapping
    public ResponseEntity<?> addNewMessage(@RequestBody MessageAllInfo messageAllInfo,
                                           Principal principal) {
        MessageDB messageDB = converter.webMessageToDatabase(messageAllInfo.getMessage());
        int messId = mailService.addMessage(messageDB, principal);
        for (AddressesWebDTO addressees : messageAllInfo.getAddresses()) {
            mailService.createNewLink(converter.webAddressesToDatabase(addressees).getId(),
                    (long) messId);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageWebDTO> getMessageOnID(@PathVariable("id") Long id, Principal principal) {
        return new ResponseEntity<>(converter.databaseToWebMessage(mailService.getMessageOnId(id, principal)),
                HttpStatus.OK);
    }

    @PostMapping("/del/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id, Principal principal) {
        mailService.deleteMessage(id, principal);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public  ResponseEntity<?> updateMessage(@PathVariable("id") Long id,
            @RequestBody MessageAllInfo messageAllInfo, Principal principal) {
        MessageDB messageDB = converter.webMessageToDatabase(messageAllInfo.getMessage());
        mailService.updateMessage(messageDB, principal);
        mailService.deleteLinks(messageDB.getId(), principal);
        for (AddressesWebDTO add : messageAllInfo.getAddresses()) {
            mailService.createNewLink(converter.webAddressesToDatabase(add).getId(),
                    messageDB.getId());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
