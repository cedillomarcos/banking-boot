package com.bankexample.banking.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictAlreadyExistsException extends ResponseStatusException {

    public ConflictAlreadyExistsException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
