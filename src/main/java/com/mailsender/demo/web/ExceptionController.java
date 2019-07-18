package com.mailsender.demo.web;

import com.mailsender.demo.exceptions.DatabaseException;
import com.mailsender.demo.exceptions.DatabaseExceptionsHandlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DatabaseException.class})
    public ResponseEntity<DatabaseExceptionsHandlers> exceptionChangeUncownUser(DatabaseException ex) {
        return new ResponseEntity<DatabaseExceptionsHandlers>(ex.getDatabaseExceptionsHandler(), HttpStatus.NOT_FOUND);
    }
}
