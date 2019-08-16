package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;

import javax.sql.DataSource;
import java.util.List;

public interface DatabaseAccessor {
    List<AddresseesDB> getAllAddresses();
    List<AddresseesDB> getAddresseesByEmail(String name);
    int addAddressees(AddresseesDB addresseesDB);
    int updateAddresses(AddresseesDB addresseesDB);

    int saveMessage(MessageDB messageDB);
    int updateMessage(MessageDB messageDB);
    MessageDB getMessageOnID(Long id);
    int deleteMessage(Long id);
    List<MessageDB> getNMessage(Long N, Long d);
    List<MessageDB> getAllMessage();


    int deleteLinks(Long messageID);
    int createNewLink(Long idAddressees, Long idMessage);
    List<AddresseesDB> getAddressesOnMessage(Long id);
}
