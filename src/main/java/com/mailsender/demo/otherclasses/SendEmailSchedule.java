package com.mailsender.demo.otherclasses;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.csv.implement.CSVParserImpl;
import com.mailsender.demo.database.dto.AddresseesDB;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class SendEmailSchedule {

    private final JavaMailSender emailSender;
    private final DatabaseAccessor databaseAccessor;
    private final CSVParserImpl csvParserImpl;

    public SendEmailSchedule(JavaMailSender emailSender,
                             DatabaseAccessor databaseAccessor, CSVParserImpl csvParserImpl) {
        this.emailSender = emailSender;
        this.databaseAccessor = databaseAccessor;
        this.csvParserImpl = csvParserImpl;
    }

    private void executed() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Naglui.eretick@yandex.ru");
        for (AddresseesDB addresseesDB : databaseAccessor.getAllAddresses()) {
            message.setTo(addresseesDB.getEmail());
            message.setSubject("Привет от Бэрримора");
            message.setText("Пора пить чай");
            this.emailSender.send(message);
        }
    }

    @Scheduled(cron="0 0 17 * * MON-FRI")
   // @Scheduled(fixedDelay = 5000)
   // @Scheduled(cron="*/5 * * * * MON-FRI")
    public void sendEmail() throws IOException {
        String schedule = csvParserImpl.getCurrentSchedule();
        List<String> checkDay = Arrays
                .asList(schedule.replaceAll("[^\\d,]", "")
                .split(","));
        log.info(checkDay.toString());
        String date = new SimpleDateFormat("dd").format(new GregorianCalendar().getTime());
        log.info(date);
        if (checkDay.contains(date)) {
            log.info("Sending emails");
            executed();
        }
    }
}
