package com.mailsender.demo.OtherClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendEmailSchedule {

    @Autowired
    public JavaMailSender emailSender;

    //@Scheduled(cron="0 0 17 * * MON-FRI")
    @Scheduled(fixedDelay = 5000)
    public void sendEmail() {
        System.out.println(new Date());
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("Naglui.eretick@yandex.ru");
        message.setTo("lezgyan@yandex.ru");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        this.emailSender.send(message);
    }
}
