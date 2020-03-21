package com.mailsender.demo.service;


import com.mailsender.demo.database.AddresseesRepository;
import com.mailsender.demo.database.MailRepository;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.registration.repository.UserRepository;
import com.mailsender.demo.web.dto.MessageWebDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MailService {
    @Autowired
    private Converter converter;
    @Autowired
    private UserRepository userRepository;

    private final MailRepository addresseesRepository;

    @Autowired
    public MailService(MailRepository mailRepository) {
        this.addresseesRepository = mailRepository;
    }

    private Long getUserID(Principal principal) {
        return userRepository.findUserByEmail(principal.getName()).getId();
    }

    public int addMessage(MessageDB messageDB, Principal user) {
        return this.addresseesRepository.saveMessage(messageDB, getUserID(user));
    }


    public MessageDB getMessageOnId(Long idMess, Principal user) {
        return this.addresseesRepository.getMessageOnID(idMess, getUserID(user));
    }

    public int createNewLink(Long addresseesID, Long messageID) {
        return this.addresseesRepository.createNewLink(addresseesID, messageID);
    }

    public int deleteMessage(Long idMessage, Principal user) {
        this.addresseesRepository.deleteLinks(idMessage, getUserID(user));
        return this.addresseesRepository.deleteMessage(idMessage, getUserID(user));
    }

    public int deleteLinks(Long idMessage, Principal user) {
        return this.addresseesRepository.deleteLinks(idMessage, getUserID(user));
    }

    public int updateMessage(MessageDB messageDB, Principal user) {
        return this.addresseesRepository.updateMessage(messageDB, getUserID(user));
    }

    public List<MessageDB> getNMessage(Long N, Long d, Principal user) {
        return this.addresseesRepository.getNMessage(N, d, getUserID(user));
    }

    public List<MessageWebDTO> getMessageNSbj(Long N, Long d, int len, Principal user) {
        List<MessageWebDTO> message = addresseesRepository
                .getNMessage(N, d, getUserID(user))
                .stream().map((a)-> {
                    MessageWebDTO mess = converter.databaseToWebMessage(a);
                    if (len > 0) {
                        String mes = mess.getEmail();
                        mess.setEmail(mes.substring(0, Math.min(mes.length(), len)));
                    }
                    return mess;
                }).collect(Collectors.toList());
        return message;
    }
}
