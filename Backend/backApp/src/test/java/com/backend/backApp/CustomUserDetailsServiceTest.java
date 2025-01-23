package com.backend.backApp;

import com.backend.backApp.models.Account;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void load_user_by_username_when_user_exists(){
        Account account = new Account("test@gmail.com", "Test", "User", "encodedPassword");

        when(accountRepository.findByEmail("test@gmail.com")).thenReturn(account);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@gmail.com");

        assertEquals("test@gmail.com",userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_basicuser")));
    }

    @Test
    public void load_user_by_username_when_user_does_not_exist(){
        when(accountRepository.findByEmail("notknown@test.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->{
            customUserDetailsService.loadUserByUsername("notknown@test.com");
        });
    }
}
