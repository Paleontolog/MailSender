package com.mailsender.demo.csv.implement;

import com.mailsender.demo.csv.Downloader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class SimpleDownloader implements Downloader {

    private String parseHTML(String url) {
        String res = null;
        try {
            log.info(url);
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".download");
            res = elements.first().select("a").attr("href");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log.info(res != null ? res : "Parsind error");
        return res;
    }

    public void download(String url, String folder) {
        log.info(url);
        log.info(folder);
        InputStream stream = null;
        try {
            String downloadURL = parseHTML(url);
            URL u = new URL(downloadURL);
            stream = u.openStream();
            Files.copy(stream, new File(folder).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Downloading error", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error("Close input stream error", e);
                    e.printStackTrace();
                }
            }
        }
    }
}
