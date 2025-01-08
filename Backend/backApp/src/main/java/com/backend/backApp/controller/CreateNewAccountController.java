package com.backend.backApp.controller;


import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.validators.AccountValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CreateNewAccountController {

    private final PasswordEncoder encoder;

    private final AccountRepository accountRepository;

    public CreateNewAccountController(PasswordEncoder encoder, AccountRepository accountRepository) {
        this.encoder = encoder;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/createnewaccount")
    public ResponseEntity<AccountDTO> createNewAccount(@RequestBody Account account) {
        AccountValidator.execute(account);
        Account savedAccount = accountRepository.save(
                new Account(
                        account.getEmail(),
                        account.getFirstName(),
                        account.getLastName(),
                        encoder.encode(account.getPassword())
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountDTO(savedAccount));
    }
}
