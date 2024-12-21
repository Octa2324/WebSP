package com.backend.backApp.service;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteAccountService implements Command<Integer,Void>{

    private AccountRepository accountRepository;

    public DeleteAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {

        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isPresent()){
            accountRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new AccountNotFoundException();
    }
}
