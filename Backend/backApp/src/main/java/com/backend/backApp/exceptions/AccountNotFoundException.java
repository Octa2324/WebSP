package com.backend.backApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(){
        super(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage());
    }
}
