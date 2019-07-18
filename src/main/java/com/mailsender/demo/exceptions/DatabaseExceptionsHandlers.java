package com.mailsender.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseExceptionsHandlers {
    USER_NOT_FOUND("Пользователь с таким ID не найден");
    private String message;
}
