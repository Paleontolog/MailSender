package com.mailsender.demo.csv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Downloader {

    //@Value("${schedule.url}")
    private String url = "https://data.gov.ru/opendata/7708660670-proizvcalendar";
    private String folder = "src/main/resources/schedulefiles/";

    private String parseHTML() {
        String res = null;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".download");
            res = elements.first().select("a").attr("href");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void download() {
        try {
            String filePath = folder + "schedule.csv";
            String downloadURL = parseHTML();
            URL u = new URL(downloadURL);
            InputStream stream = u.openStream();
            Files.copy(stream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Downloader d = new Downloader();
        d.download();
    }

}