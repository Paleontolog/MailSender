package com.mailsender.demo.csv;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;


public class CSVParser {

    private String path = "src/main/resources/schedulefiles/schedule.csv";

    public String getCurrentSchedule () {
        String cronString = null;
        try (CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 0);){
            //Скачиваем файл csv
            (new Downloader()).download();


            String[] header = reader.readNext();
            String[] line ;
            int currentYear = Year.now().getValue();
            System.out.println(currentYear);
            // В строке месяцы начинаются с 1
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            System.out.println(currentMonth);
            while ((line = reader.readNext()) != null &&
                    currentYear != Integer.parseInt(line[0]));
            cronString = line[currentMonth];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cronString;
    }

    public static void main(String[] args) {
        CSVParser p = new CSVParser();
        System.out.println(p.getCurrentSchedule());
    }
}
