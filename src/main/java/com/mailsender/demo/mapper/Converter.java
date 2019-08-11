package com.mailsender.demo.mapper;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.web.AddressesWebDTO;
import com.mailsender.demo.web.MessageWebDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Converter {
    AddresseesDB webAddressesToDatabase(AddressesWebDTO web);
    AddressesWebDTO databaseToWebAddressees(AddresseesDB database);

    MessageDB webMessageToDatabase(MessageWebDTO messageWebDTO);
    MessageWebDTO databaseToWebMessage(MessageDB messageDB);
}
