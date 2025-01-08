package com.backend.backApp.service;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.models.UpdateAccountCommand;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.validators.AccountValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAccountService implements Command<UpdateAccountCommand, AccountDTO>{

    private AccountRepository accountRepository;

    private final PasswordEncoder encoder;


    public UpdateAccountService(AccountRepository accountRepository, PasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<AccountDTO> execute(UpdateAccountCommand command) {
        Optional<Account> accountOptional = accountRepository.findById(command.getId());
        if(accountOptional.isPresent()){
            Account account = command.getAccount();
            account.setId(command.getId());

            if (account.getPassword() != null && !account.getPassword().isBlank()) {
                account.setPassword(encoder.encode(account.getPassword()));
            }

            AccountValidator.executeUpdate(account);
            accountRepository.save(account);
            return ResponseEntity.ok(new AccountDTO(account));
        }

        throw new AccountNotFoundException();
    }
}
