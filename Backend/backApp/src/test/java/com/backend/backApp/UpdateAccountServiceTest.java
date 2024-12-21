package com.backend.backApp;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.models.UpdateAccountCommand;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.UpdateAccountService;
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

public class UpdateAccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UpdateAccountService updateAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_account_exists_when_update_account_service_return_account_dto(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        Account updatedAccount = new Account();
        updatedAccount.setId(1);
        updatedAccount.setEmail("updated@gmail.com");
        updatedAccount.setPassword("newpassword");
        updatedAccount.setFirstName("Jon");
        updatedAccount.setLastName("Doe");

        UpdateAccountCommand command = new UpdateAccountCommand(1,updatedAccount);

        when(accountRepository.findById(1)).thenReturn(Optional.of(account));

        when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);

        ResponseEntity<AccountDTO> response = updateAccountService.execute(command);

        assertEquals(ResponseEntity.ok(new AccountDTO(updatedAccount)), response);

        verify(accountRepository, times(1)).findById(1);
        verify(accountRepository,times(1)).save(updatedAccount);
    }

    @Test
    public void given_account_does_not_exist_when_update_service_throw_account_not_found_exception(){
        when(accountRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> updateAccountService.execute(new UpdateAccountCommand(1,new Account())));

        verify(accountRepository, times(1)).findById(1);
        verify(accountRepository, never()).save(any());

    }
}
