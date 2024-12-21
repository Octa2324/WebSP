package com.backend.backApp;


import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.service.SearchAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchAccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private SearchAccountService  searchAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_account_exists_when_search_account_service_return_list_of_account_dto(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        List<Account> accounts = List.of(account);

        AccountDTO accountDTO = new AccountDTO(account);
        List<AccountDTO> accountDTOS = List.of(accountDTO);

        when(accountRepository.findByFirstNameContaining("Jon")).thenReturn(accounts);

        ResponseEntity<List<AccountDTO>> response = searchAccountService.execute("Jon");

        assertEquals(ResponseEntity.ok(accountDTOS), response);

        verify(accountRepository,times(1)).findByFirstNameContaining("Jon");
    }

}
