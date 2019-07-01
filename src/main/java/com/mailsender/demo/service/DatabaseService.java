package com.mailsender.demo.service;

import com.mailsender.demo.model.Addressees;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DatabaseService {
    private static List<Addressees> listOfEmails = new LinkedList<>();

    static {
        listOfEmails.add(new Addressees("1111111111111111111111"));
        listOfEmails.add(new Addressees("2222222222222222222222"));
        listOfEmails.add(new Addressees("3333333333333333333333"));
    }

    public List<Addressees> getListOfEmails() {
        return listOfEmails;
    }
}
