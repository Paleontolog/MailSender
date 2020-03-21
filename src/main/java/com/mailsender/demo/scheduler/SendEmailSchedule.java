package com.mailsender.demo.scheduler;

import com.mailsender.demo.csv.CSVParser;
import com.mailsender.demo.database.AddresseesRepository;
import com.mailsender.demo.database.MailRepository;
import com.mailsender.demo.database.dto.AddresseesDB;
import com.mailsender.demo.database.dto.MessageDB;
import com.mailsender.demo.registration.model.User;
import com.mailsender.demo.registration.repository.UserRepository;
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
    private AddresseesRepository addresseesRepository;
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private CSVParser csvParser;
    @Autowired
    UserRepository userRepository;
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
        for (User user : userRepository.getAllUsers()) {
            Long id = user.getId();
            for (MessageDB messageDB : mailRepository.getAllMessage(id)) {
                for (AddresseesDB addresseesDB : addresseesRepository.getAddressesOnMessage(messageDB.getId(), id)) {
                    message.setTo(addresseesDB.getEmail());
                    message.setSubject(messageDB.getSubject());
                    message.setText(messageDB.getEmail());
                    this.emailSender.send(message);
                }
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
