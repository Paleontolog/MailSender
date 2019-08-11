package com.mailsender.demo.database;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;

import javax.sql.DataSource;
import java.util.List;

public interface DatabaseAccessor {
    List<AddresseesDB> getAllAddresses();
    int addAddressees(AddresseesDB addresseesDB);
    int updateAddresses(AddresseesDB addresseesDB);

    int saveMessage(MessageDB messageDB);
    int updateMessage(MessageDB messageDB);
    MessageDB getMessageOnID(Long id);
    List<MessageDB> getAllMessageSbj();
    int deleteMessage(Long id);
    List<MessageDB> getAllMessage();


    int deleteLinks(Long messageID);
    int createNewLink(Long idAddressees, Long idMessage);
    List<AddresseesDB> getAddressesOnMessage(Long id);
}
