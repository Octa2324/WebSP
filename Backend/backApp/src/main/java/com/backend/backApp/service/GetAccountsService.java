package com.backend.backApp.service;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccountsService implements  Query<Void,List<AccountDTO>>{

    private AccountRepository accountRepository;

    public GetAccountsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> execute(Void input) {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream().map(AccountDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOS);
    }
}
