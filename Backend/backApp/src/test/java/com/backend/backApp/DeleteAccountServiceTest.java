package com.backend.backApp;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.DeleteAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteAccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DeleteAccountService deleteAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_account_exists_when_delete_account_service_return_nothing(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        when(accountRepository.findById(1)).thenReturn(Optional.of(account));

        ResponseEntity<Void> response = deleteAccountService.execute(1);

        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);

        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    public void given_account_does_not_exist_when_delete_account_service_throw_account_not_found_exception(){
        when(accountRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> deleteAccountService.execute(1));

        verify(accountRepository, times(1)).findById(1);
    }
}
