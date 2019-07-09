package com.mailsender.demo.model;

public class Addressees {

    private final Long id;
    private final String email;

    public Addressees(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

