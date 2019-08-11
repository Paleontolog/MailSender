package com.mailsender.demo.service;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseAccessor databaseAccessor;

    @Autowired
    public DatabaseService(DatabaseAccessor databaseAccessor) {
        this.databaseAccessor = databaseAccessor;
    }

    public List<AddresseesDB> getListOfAddresses() {
        return databaseAccessor.getAllAddresses();
    }

    public int addAddresses(AddresseesDB addresseesDB) {
        return databaseAccessor.addAddressees(addresseesDB);
    }

    public int updateAddresses(AddresseesDB addresseesDB) {
        return databaseAccessor.updateAddresses(addresseesDB);
    }

    public List<MessageDB> getListOfSubjects() { return this.databaseAccessor.getAllMessageSbj(); }

    public int addMessage(MessageDB messageDB) {return this.databaseAccessor.saveMessage(messageDB);}

    public MessageDB getMessageOnId(Long id) {return this.databaseAccessor.getMessageOnID(id);}

    public int createNewLink(Long addresseesID, Long messageID) {
        return this.databaseAccessor.createNewLink(addresseesID, messageID);
    }

    public List<AddresseesDB> getAddresseesOnMessage(Long idMessage) {
        return this.databaseAccessor.getAddressesOnMessage(idMessage);
    }

    public int deleteMessage(Long idMessage) {return this.databaseAccessor.deleteMessage(idMessage);}

    public int deleteLinks(Long idMessage) {return this.databaseAccessor.deleteLinks(idMessage);}

    public int updateMessage(MessageDB messageDB) {return this.databaseAccessor.updateMessage(messageDB);}

}
