package com.backend.backApp;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.CreateAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateAccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CreateAccountService createAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_account_service(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        AccountDTO accountDTO = new AccountDTO(account);

        when(accountRepository.save(account)).thenReturn(account);

        ResponseEntity<AccountDTO> response = createAccountService.execute(account);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(accountDTO) ,response);

        verify(accountRepository, times(1)).save(account);
    }
}
