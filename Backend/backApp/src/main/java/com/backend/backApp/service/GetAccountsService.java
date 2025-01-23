package com.backend.backApp.service;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.AccountDTO;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.repository.FriendshipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAccountsService implements  Query<Void,List<AccountDTO>>{

    private final AccountRepository accountRepository;
    private final FriendshipRepository friendshipRepository;

    public GetAccountsService(AccountRepository accountRepository, FriendshipRepository friendshipRepository) {
        this.accountRepository = accountRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> execute(Void input) {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream().map(AccountDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOS);
    }



    public ResponseEntity<List<AccountDTO>> getAccountFriends(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        List<AccountDTO> friends = new ArrayList<>();

        friends.addAll(friendshipRepository.findByAccount1(account).stream()
                .map(friendship -> new AccountDTO(friendship.getAccount2()))
                .collect(Collectors.toList()));
        friends.addAll(friendshipRepository.findByAccount2(account).stream()
                .map(friendship -> new AccountDTO(friendship.getAccount1()))
                .collect(Collectors.toList()));

        return ResponseEntity.ok(friends);
    }
}
