package com.mailsender.demo.csv;

import java.io.IOException;

public interface Downloader {
    public void download(String url, String folder) throws IOException;
}