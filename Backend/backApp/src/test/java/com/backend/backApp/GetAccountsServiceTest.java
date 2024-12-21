package com.backend.backApp;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.GetAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAccountsServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GetAccountsService getAccountsService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_accounts_exists_when_get_account_service_return_accounts_dtos(){
        Account account1 = new Account();
        account1.setId(1);
        account1.setEmail("test@gmail.com");
        account1.setPassword("melutzu");
        account1.setFirstName("Jon");
        account1.setLastName("Doe");

        Account account2 = new Account();
        account2.setId(2);
        account2.setEmail("test2@gmail.com");
        account2.setPassword("mielu");
        account2.setFirstName("Lebron");
        account2.setLastName("Marius");

        List<Account> accounts = List.of(account1,account2);

        AccountDTO dto1 = new AccountDTO(account1);
        AccountDTO dto2 = new AccountDTO(account2);
        List<AccountDTO> accountDTOS = List.of(dto1,dto2);

        when(accountRepository.findAll()).thenReturn(accounts);

        ResponseEntity<List<AccountDTO>> response = getAccountsService.execute(null);

        assertEquals(ResponseEntity.ok(accountDTOS), response);

        verify(accountRepository, times(1)).findAll();
    }

}
