package com.mailsender.demo.registration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  Role {
    USER("USER");
    private String role;
}
