package com.backend.backApp.service;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAccountService implements Query<Integer, AccountDTO> {

    private final AccountRepository accountRepository;

    public GetAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<AccountDTO> execute(Integer input) {
        Optional<Account> accountOptional = accountRepository.findById(input);
        if(accountOptional.isPresent()){
            return ResponseEntity.ok(new AccountDTO(accountOptional.get()));
        }
        throw  new AccountNotFoundException();
    }

    public ResponseEntity<AccountDTO> getAccountByEmail(String email){
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findByEmail(email));
        if(accountOptional.isPresent()){
            return ResponseEntity.ok(new AccountDTO(accountOptional.get()));
        }
        throw new AccountNotFoundException();
    }
}
