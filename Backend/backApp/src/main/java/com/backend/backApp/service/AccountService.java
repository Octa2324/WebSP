package com.backend.backApp.service;

import com.backend.backApp.models.Account;
import com.backend.backApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account){
        accountRepository.save(account);
        return account;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
