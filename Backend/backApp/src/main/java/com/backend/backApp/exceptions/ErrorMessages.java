package com.backend.backApp.exceptions;

public enum ErrorMessages {
    ACCOUNT_NOT_FOUND("Account not found"),
    EMAIL_REQUIRED("Email is required for account creation"),
    PASSWORD_REQUIRED("Password is required for account creation"),
    EMAIL_REQUIRED_UPDATE("Email is required for updating account"),
    PASSWORD_REQUIRED_UPDATE("Password is required for updating account"),
    USERNAME_NOT_FOUND("Username not found");

    private String message;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
