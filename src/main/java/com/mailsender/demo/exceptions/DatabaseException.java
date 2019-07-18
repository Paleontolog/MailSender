package com.mailsender.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DatabaseException extends RuntimeException {
    private DatabaseExceptionsHandlers databaseExceptionsHandler;
}
