package com.backend.backApp;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.Friendship;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.repository.FriendshipRepository;
import com.backend.backApp.service.AddAndDeleteFriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AddAndDeleteFriendServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    FriendshipRepository friendshipRepository;

    @InjectMocks
    AddAndDeleteFriendService addAndDeleteFriendService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_emails_when_addFriendByEmail_then_success() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setEmail("email1@test.com");

        Account account2 = new Account();
        account2.setId(2);
        account2.setEmail("email2@test.com");

        when(accountRepository.findByEmail("email1@test.com")).thenReturn(account1);
        when(accountRepository.findByEmail("email2@test.com")).thenReturn(account2);
        when(friendshipRepository.existsByAccount1AndAccount2(account1, account2)).thenReturn(false);
        when(friendshipRepository.existsByAccount1AndAccount2(account2, account1)).thenReturn(false);

        ResponseEntity<String> response = addAndDeleteFriendService.addFriendByEmail("email1@test.com", "email2@test.com");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Friend added successfully.", response.getBody());
        verify(friendshipRepository, times(1)).save(any(Friendship.class));
    }

    @Test
    public void given_same_email_when_addFriendByEmail_then_fail() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setEmail("email@test.com");

        when(accountRepository.findByEmail("email@test.com")).thenReturn(account1);

        ResponseEntity<String> response = addAndDeleteFriendService.addFriendByEmail("email@test.com", "email@test.com");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cannot be friend with yourself.", response.getBody());
        verify(friendshipRepository, never()).save(any(Friendship.class));
    }

    @Test
    public void given_nonexistent_email_when_addFriendByEmail_then_throw_exception() {
        when(accountRepository.findByEmail("nonexistent@test.com")).thenReturn(null);

        try {
            addAndDeleteFriendService.addFriendByEmail("nonexistent@test.com", "email2@test.com");
        } catch (AccountNotFoundException e) {
            assertEquals(AccountNotFoundException.class, e.getClass());
        }

        verify(friendshipRepository, never()).save(any(Friendship.class));
    }

    @Test
    public void given_valid_emails_when_removeFriendByEmail_then_success() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setEmail("email1@test.com");

        Account account2 = new Account();
        account2.setId(2);
        account2.setEmail("email2@test.com");

        Friendship friendship = new Friendship(account1, account2);

        when(accountRepository.findByEmail("email1@test.com")).thenReturn(account1);
        when(accountRepository.findByEmail("email2@test.com")).thenReturn(account2);
        when(friendshipRepository.findByAccount1AndAccount2(account1, account2)).thenReturn(friendship);

        ResponseEntity<String> response = addAndDeleteFriendService.removeFriendByEmail("email1@test.com", "email2@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Friend removed successfully.", response.getBody());
        verify(friendshipRepository, times(1)).delete(friendship);
    }

    @Test
    public void given_nonexistent_friendship_when_removeFriendByEmail_then_fail() {
        Account account1 = new Account();
        account1.setId(1);
        account1.setEmail("email1@test.com");

        Account account2 = new Account();
        account2.setId(2);
        account2.setEmail("email2@test.com");

        when(accountRepository.findByEmail("email1@test.com")).thenReturn(account1);
        when(accountRepository.findByEmail("email2@test.com")).thenReturn(account2);
        when(friendshipRepository.findByAccount1AndAccount2(account1, account2)).thenReturn(null);

        ResponseEntity<String> response = addAndDeleteFriendService.removeFriendByEmail("email1@test.com", "email2@test.com");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("They are not friends.", response.getBody());
        verify(friendshipRepository, never()).delete(any(Friendship.class));
    }
}

