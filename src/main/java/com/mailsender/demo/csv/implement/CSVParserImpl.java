package com.mailsender.demo.csv.implement;

import au.com.bytecode.opencsv.CSVReader;
import com.mailsender.demo.csv.CSVParser;
import com.mailsender.demo.csv.Downloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

@Component
public class CSVParserImpl implements CSVParser {

    private Downloader downloader;

    @Autowired
    public CSVParserImpl(Downloader downloader) {
        this.downloader = downloader;
    }

    private static Logger logger =  LoggerFactory.getLogger(CSVParserImpl.class);

    private String path = "src/main/resources/schedulefiles/schedule.csv";

    public String getCurrentSchedule () throws IOException {
        String cronString = null;
        downloader.download();
        try (CSVReader reader = new CSVReader(new FileReader(path), ',', '"', 1)){
            String[] line ;
            int currentYear = Year.now().getValue();
            logger.info(Integer.toString(currentYear));
            // В строке месяцы начинаются с 1
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            logger.info(Integer.toString(currentMonth));

            while ((line = reader.readNext()) != null &&
                    currentYear != Integer.parseInt(line[0]));
            assert line != null;
            cronString = line[currentMonth];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cronString;
    }
}
