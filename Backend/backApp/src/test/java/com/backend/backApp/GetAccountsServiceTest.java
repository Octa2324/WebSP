package com.backend.backApp;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.models.Friendship;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.repository.FriendshipRepository;
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

    @Mock
    private FriendshipRepository friendshipRepository;

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

    @Test
    public void given_account_friends_exists_when_get_account_friends_return_list_of_account_dtos(){
        Account account = new Account();
        account.setId(1);
        account.setEmail("test@gmail.com");
        account.setPassword("melutzu");
        account.setFirstName("Jon");
        account.setLastName("Doe");

        Account friend1 = new Account();
        friend1.setId(2);
        friend1.setEmail("friend1@gmail.com");
        friend1.setPassword("password1");
        friend1.setFirstName("Friend");
        friend1.setLastName("One");

        Account friend2 = new Account();
        friend2.setId(3);
        friend2.setEmail("friend2@gmail.com");
        friend2.setPassword("password2");
        friend2.setFirstName("Friend");
        friend2.setLastName("Two");

        Friendship friendship1 = new Friendship(account, friend1);
        Friendship friendship2 = new Friendship(friend2, account);

        when(accountRepository.findByEmail("test@gmail.com")).thenReturn(account);
        when(friendshipRepository.findByAccount1(account)).thenReturn(List.of(friendship1));
        when(friendshipRepository.findByAccount2(account)).thenReturn(List.of(friendship2));

        AccountDTO friendDTO1 = new AccountDTO(friend1);
        AccountDTO friendDTO2 = new AccountDTO(friend2);
        List<AccountDTO> expectedFriends = List.of(friendDTO1, friendDTO2);

        ResponseEntity<List<AccountDTO>> response = getAccountsService.getAccountFriends("test@gmail.com");

        assertEquals(ResponseEntity.ok(expectedFriends), response);

        verify(accountRepository, times(1)).findByEmail("test@gmail.com");
        verify(friendshipRepository, times(1)).findByAccount1(account);
        verify(friendshipRepository, times(1)).findByAccount2(account);
    }

}
