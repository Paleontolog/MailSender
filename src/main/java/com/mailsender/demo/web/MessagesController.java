package com.mailsender.demo.web;

import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
    @RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private DatabaseService databaseService;
    @Autowired
    private Converter converter;

    @PutMapping
    public ResponseEntity<?> addNewMessage(@RequestBody MessageAllInfo messageAllInfo) {
        MessageDB messageDB = converter.webMessageToDatabase(messageAllInfo.getMessage());
        int messId = databaseService.addMessage(messageDB);
        for (AddressesWebDTO addressees : messageAllInfo.getAddresses()) {
            databaseService.createNewLink(converter.webAddressesToDatabase(addressees).getId(),
                    (long) messId);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageWebDTO> getMessageOnID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(converter.databaseToWebMessage(databaseService.getMessageOnId(id)),
                HttpStatus.OK);
    }

    @PostMapping("/del/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
        databaseService.deleteMessage(id);
        databaseService.deleteLinks(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public  ResponseEntity<?> updateMessage(@PathVariable("id") Long id,
            @RequestBody MessageAllInfo messageAllInfo) {
        MessageDB messageDB = converter.webMessageToDatabase(messageAllInfo.getMessage());
        databaseService.updateMessage(messageDB);
        databaseService.deleteLinks(messageDB.getId());
        for (AddressesWebDTO add : messageAllInfo.getAddresses()) {
            databaseService.createNewLink(converter.webAddressesToDatabase(add).getId(),
                    messageDB.getId());
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
