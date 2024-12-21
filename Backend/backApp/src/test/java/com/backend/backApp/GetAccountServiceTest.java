package com.backend.backApp;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.GetAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetAccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GetAccountService getAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_account_exists_when_get_account_service_return_account_dto(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        when(accountRepository.findById(1)).thenReturn(Optional.of(account));

        ResponseEntity<AccountDTO> response = getAccountService.execute(1);

        assertEquals(ResponseEntity.ok(new AccountDTO(account)), response);

        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    public void given_account_exists_when_get_account_by_email_return_account_dto(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        when(accountRepository.findByEmail("test@gmail.com")).thenReturn(account);

        ResponseEntity<AccountDTO> response = getAccountService.getAccountByEmail("test@gmail.com");

        assertEquals(ResponseEntity.ok(new AccountDTO(account)), response);

        verify(accountRepository, times(1)).findByEmail("test@gmail.com");
    }

    @Test
    public void given_account_does_not_exists_when_account_get_service_throw_account_not_found_exception(){
        when(accountRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> getAccountService.execute(1));

        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    public void given_account_does_not_exists_when_account_get_by_email_throw_account_not_found_exception(){
        when(accountRepository.findByEmail("test@gmail.com")).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> getAccountService.getAccountByEmail("test@gmail.com"));

        verify(accountRepository, times(1)).findByEmail("test@gmail.com");

    }
}
