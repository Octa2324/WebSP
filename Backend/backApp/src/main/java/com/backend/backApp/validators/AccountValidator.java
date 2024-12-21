package com.backend.backApp.validators;

import com.backend.backApp.exceptions.AccountNotValidException;
import com.backend.backApp.exceptions.ErrorMessages;
import com.backend.backApp.models.Account;
import io.micrometer.common.util.StringUtils;

public class AccountValidator {
    private  AccountValidator(){

    }

    public static void execute(Account account){
        if(StringUtils.isEmpty(account.getEmail())){
            throw new AccountNotValidException(ErrorMessages.EMAIL_REQUIRED.getMessage());
        }

        if(StringUtils.isEmpty(account.getPassword())){
            throw new AccountNotValidException(ErrorMessages.PASSWORD_REQUIRED.getMessage());
        }
    }

    public static void executeUpdate(Account account){
        if(StringUtils.isEmpty(account.getEmail())){
            throw new AccountNotValidException(ErrorMessages.EMAIL_REQUIRED_UPDATE.getMessage());
        }

        if(StringUtils.isEmpty(account.getPassword())){
            throw new AccountNotValidException(ErrorMessages.PASSWORD_REQUIRED_UPDATE.getMessage());
        }
    }
}
