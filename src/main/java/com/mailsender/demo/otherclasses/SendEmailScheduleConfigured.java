//package com.mailsender.demo.otherclasses;
//
//import com.mailsender.demo.csv.implement.CSVParserImpl;
//import com.mailsender.demo.database.DatabaseAccessor;
//import com.mailsender.demo.database.dto.AddresseesDB;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//
//import java.util.Date;
//
//@SpringBootApplication
//@EnableScheduling
//public class SendEmailScheduleConfigured implements SchedulingConfigurer {
//
//    private final JavaMailSender emailSender;
//    private final com.mailsender.demo.database.DatabaseAccessor databaseAccessor;
//
//
//    public SendEmailScheduleConfigured(JavaMailSender emailSender, com.mailsender.demo.database.DatabaseAccessor databaseAccessor) {
//        this.emailSender = emailSender;
//        this.databaseAccessor = databaseAccessor;
//    }
//
//    private void executed() {
//        System.out.println(new Date());
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("Naglui.eretick@yandex.ru");
//        for (AddresseesDB addressees : databaseAccessor.getAllAddresses()) {
//            message.setTo(addressees.getEmail());
//            message.setSubject("Привет от Бэрримора");
//            message.setText("Пора пить чай");
//            this.emailSender.send(message);
//        }
//    }
//
//    private String prepareCron() {
//        String cronString = new CSVParserImpl().getCurrentSchedule();
//        cronString = cronString.replaceAll("[^\\d,]", "");
//        cronString = "*/1 0 17 " + cronString + " * ?";
//        return cronString;
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
//                                                  @Override
//                                                  public void run() {
//                                                      executed();
//                                                  }
//                                              },
//                new Trigger() {
//                    @Override
//                    public Date nextExecutionTime(TriggerContext triggerContext) {
//                        CronTrigger trigger = new CronTrigger(prepareCron());
//                        return trigger.nextExecutionTime(triggerContext);
//                    }
//                }
//        );
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SendEmailScheduleConfigured.class, args);
//    }
//}
