package com.mailsender.demo.OtherClasses;

import com.mailsender.demo.database.DatabaseAccessor;
import com.mailsender.demo.model.Addressees;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendEmailSchedule {

    private final JavaMailSender emailSender;
    private final DatabaseAccessor databaseAccessor;

    public SendEmailSchedule(JavaMailSender emailSender, DatabaseAccessor databaseAccessor) {
        this.emailSender = emailSender;
        this.databaseAccessor = databaseAccessor;
    }

    @Scheduled(cron="0 0 17 * * MON-FRI")
   // @Scheduled(fixedDelay = 5000)
    public void sendEmail() {
        System.out.println(new Date());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Naglui.eretick@yandex.ru");
        for (Addressees addressees : databaseAccessor.getAllAddresses()) {
            System.out.println(addressees.getId() + " " + addressees.getEmail());
            message.setTo(addressees.getEmail());
            message.setSubject("Приветики, Ань");
            message.setText("Немного спама вам в почту XD Таки это моё задание)) Практика весёлая штука");
            this.emailSender.send(message);
        }
    }
}
