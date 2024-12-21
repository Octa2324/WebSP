package com.backend.backApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNotValidException extends RuntimeException{
    public AccountNotValidException(String message){
        super(message);
    }
}
