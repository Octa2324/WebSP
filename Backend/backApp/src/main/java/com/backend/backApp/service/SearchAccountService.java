package com.backend.backApp.service;

import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAccountService implements Query<String, List<AccountDTO>> {

    private final AccountRepository accountRepository;

    public SearchAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> execute(String name) {
        return ResponseEntity.ok(accountRepository.findByFirstNameContaining(name)
                .stream()
                .map(AccountDTO::new)
                .toList()
        );
    }
}
