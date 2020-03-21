package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.MessageDB;

import java.util.List;

public interface MailRepository {
    int saveMessage(MessageDB messageDB, Long user);
    int updateMessage(MessageDB messageDB, Long user);
    MessageDB getMessageOnID(Long id, Long user);
    int deleteMessage(Long id, Long user);
    List<MessageDB> getNMessage(Long N, Long d, Long user);
    List<MessageDB> getAllMessage(Long user);

    int deleteLinks(Long messageID, Long user);
    int createNewLink(Long idAddressees, Long idMessage);
}
