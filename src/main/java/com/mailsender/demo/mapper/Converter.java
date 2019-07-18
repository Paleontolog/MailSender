package com.mailsender.demo.mapper;

import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.web.AddressesWebDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Converter {
    AddresseesDB webAddressesToDatabase(AddressesWebDTO web);
    AddressesWebDTO databaseToWebAddressees(AddresseesDB database);
}
