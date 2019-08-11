package com.mailsender.demo.otherclasses;

import com.mailsender.demo.csv.CSVParser;
import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Component
public class SendEmailSchedule {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private DatabaseAccessor databaseAccessor;
    @Autowired
    private CSVParser csvParser;
//
//    private void executed() {
//        log.info("Sending messages to emails");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("1Adeptus.Mechanicus1@gmail.com");
//        for (AddresseesDB addresseesDB : databaseAccessor.getAllAddresses()) {
//            message.setTo(addresseesDB.getEmail());
//            message.setSubject("Привет от Бэрримора");
//            message.setText("Пора пить чай");
//            this.emailSender.send(message);
//        }
//        log.info("All messages sended");
//    }


    private void executed() {
        log.info("Sending messages to emails");
        SimpleMailMessage message = new SimpleMailMessage();
        for (MessageDB messageDB : databaseAccessor.getAllMessage()) {
            for (AddresseesDB addresseesDB : databaseAccessor.getAddressesOnMessage(messageDB.getId())) {
                message.setTo(addresseesDB.getEmail());
                message.setSubject(messageDB.getSubject());
                message.setText(messageDB.getEmail());
                this.emailSender.send(message);
            }
        }
        log.info("All messages sended");
    }


    @Scheduled(cron="${schedule.cron}")
    public void sendEmail() throws IOException {
        String schedule = csvParser.getCurrentSchedule();
        List<String> checkDay = Arrays
                .asList(schedule.replaceAll("[^\\d,]", "")
                .split(","));
        log.info(checkDay.toString());
        String date = new SimpleDateFormat("dd").format(new GregorianCalendar().getTime());
        log.info(date);
        if (true) {//checkDay.contains(date)) {
            log.info("Sending emails");
            executed();
        } else {
            log.info("It's a not working day");
        }
    }
}
