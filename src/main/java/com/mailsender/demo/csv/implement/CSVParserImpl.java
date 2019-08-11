package com.mailsender.demo.csv.implement;

import au.com.bytecode.opencsv.CSVReader;
import com.mailsender.demo.csv.CSVParser;
import com.mailsender.demo.csv.Downloader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

@Slf4j
@Component
public class CSVParserImpl implements CSVParser {

    @Autowired
    private Downloader downloader;

    @Value("${schedule.url}")
    private String url;

   // private static final String folder = "/schedulefiles/schedule.csv";
   private static final String folder = "./src/main/resources/schedulefiles/schedule.csv";
    public String getCurrentSchedule() throws IOException {
        String cronString = null;

        downloader.download(url, folder);

        try (CSVReader reader = new CSVReader(new FileReader(folder), ',', '"', 1)){
            String[] line ;
            int currentYear = Year.now().getValue();
            log.info(Integer.toString(currentYear));
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            log.info(Integer.toString(currentMonth));

            while ((line = reader.readNext()) != null &&
                    currentYear != Integer.parseInt(line[0]));

            assert line != null;

            cronString = line[currentMonth];
        } catch (IOException e) {
            log.error("Parsing schedule error", e);
            e.printStackTrace();
        }
        return cronString;
    }
}
