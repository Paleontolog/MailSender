package com.mailsender.demo.OtherClasses;

import com.mailsender.demo.database.IDatabaseAccessor;
import com.mailsender.demo.model.Addressees;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendEmailSchedule {

    private final JavaMailSender emailSender;
    private final IDatabaseAccessor databaseAccessor;

    public SendEmailSchedule(JavaMailSender emailSender, IDatabaseAccessor databaseAccessor) {
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
            message.setTo(addressees.getEmail());
            message.setSubject("Привет от Бэрримора");
            message.setText("Пора пить чай");
            this.emailSender.send(message);
        }
    }
}
