package com.backend.backApp.controller;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.models.UpdateAccountCommand;
import com.backend.backApp.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AccountController {

    private final CreateAccountService createAccountService;
    private final GetAccountsService getAccountsService;
    private final UpdateAccountService updateAccountService;
    private final DeleteAccountService deleteAccountService;
    private final GetAccountService getAccountService;
    private final SearchAccountService searchAccountService;

    public AccountController(CreateAccountService createAccountService,
                             GetAccountsService getAccountsService,
                             UpdateAccountService updateAccountService,
                             DeleteAccountService deleteAccountService,
                             GetAccountService getAccountService,
                             SearchAccountService searchAccountService) {
        this.createAccountService = createAccountService;
        this.getAccountsService = getAccountsService;
        this.updateAccountService = updateAccountService;
        this.deleteAccountService = deleteAccountService;
        this.getAccountService = getAccountService;
        this.searchAccountService = searchAccountService;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account){
        return createAccountService.execute(account);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        return getAccountsService.execute(null);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Integer id){
        return getAccountService.execute(id);
    }

    @GetMapping("/account/email/{email}")
    public ResponseEntity<AccountDTO> getAccountByEmail(@PathVariable String email) { return getAccountService.getAccountByEmail(email);}

    @GetMapping("/account/search")
    public ResponseEntity<List<AccountDTO>> searchProductByName(@RequestParam String name){
        return searchAccountService.execute(name);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Integer id, @RequestBody Account account){
        return updateAccountService.execute(new UpdateAccountCommand(id,account));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){
        return deleteAccountService.execute(id);
    }

}
