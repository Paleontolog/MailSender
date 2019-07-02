package com.mailsender.demo.service;

import com.mailsender.demo.model.Addressees;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DatabaseService {
    private static List<Addressees> listOfEmails = new LinkedList<>();


    public List<Addressees> getListOfEmails() {
        return listOfEmails;
    }
}
