package com.mailsender.demo.service;

import com.mailsender.demo.database.AddresseesRepository;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.mapper.Converter;
import com.mailsender.demo.registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class AddresseesService {

    @Autowired
    private Converter converter;
    @Autowired
    private UserRepository userRepository;


    private final AddresseesRepository addresseesRepository;

    @Autowired
    public AddresseesService(AddresseesRepository addresseesRepository) {
        this.addresseesRepository = addresseesRepository;
    }

    private Long getUserID(Principal principal) {
        return userRepository.findUserByEmail(principal.getName()).getId();
    }

    public List<AddresseesDB> getListOfAddresses(Principal user) {
        return addresseesRepository.getAllAddresses(getUserID(user));
    }


    public List<AddresseesDB> getListOfAddresses(Long id) {
        return addresseesRepository.getAllAddresses(id);
    }


    public int addAddresses(AddresseesDB addresseesDB, Principal user) {
        return addresseesRepository.addAddressees(addresseesDB, getUserID(user));
    }

    public int updateAddresses(AddresseesDB addresseesDB, Principal user) {
        return addresseesRepository.updateAddresses(addresseesDB, getUserID(user));
    }

    public int updateAddresses(AddresseesDB addresseesDB, Long id) {
        return addresseesRepository.updateAddresses(addresseesDB, id);
    }

    public List<AddresseesDB> getAddresseesOnMessage(Long idMessage, Principal user) {
        return this.addresseesRepository.getAddressesOnMessage(idMessage, getUserID(user));
    }


    public List<AddresseesDB> getAddresseesByEmail(String email, Principal user) {
        return this.addresseesRepository.getAddresseesByEmail(email, getUserID(user));
    }
}