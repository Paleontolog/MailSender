package com.mailsender.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DatabaseExceptionCode {
    USER_NOT_FOUND("Пользователь с таким ID не найден"),
    MESSAGE_NOT_FOUND("Сообщение не найдено");
    private String message;
}
