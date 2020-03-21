package com.mailsender.demo.mapper;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.registration.model.User;
import com.mailsender.demo.web.dto.AddressesWebDTO;
import com.mailsender.demo.web.dto.MessageWebDTO;
import com.mailsender.demo.web.dto.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Converter {
    AddresseesDB webAddressesToDatabase(AddressesWebDTO web);
    AddressesWebDTO databaseToWebAddressees(AddresseesDB database);

    MessageDB webMessageToDatabase(MessageWebDTO messageWebDTO);
    MessageWebDTO databaseToWebMessage(MessageDB messageDB);

    @Mapping(source = "username", target = "email")
    @Mapping(source = "password", target = "password")
    User webToDBUser(WebUser webUser);
    WebUser dBUserToWeb(User webUser);
}

