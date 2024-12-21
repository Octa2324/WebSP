package com.backend.backApp;

import com.backend.backApp.exceptions.AccountNotValidException;
import com.backend.backApp.exceptions.ErrorMessages;
import com.backend.backApp.models.Account;
import com.backend.backApp.validators.AccountValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountValidatorTest {

    @Test
    public void given_account_with_empty_email_when_execute_then_throw_exception(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        AccountNotValidException exception = assertThrows(AccountNotValidException.class, () -> {
            AccountValidator.execute(account);});

        assertEquals(ErrorMessages.EMAIL_REQUIRED.getMessage(), exception.getMessage());
    }

    @Test
    public void given_account_with_empty_password_when_execute_then_throw_exception(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        AccountNotValidException exception = assertThrows(AccountNotValidException.class, () -> {
            AccountValidator.execute(account);});

        assertEquals(ErrorMessages.PASSWORD_REQUIRED.getMessage(), exception.getMessage());
    }
}
