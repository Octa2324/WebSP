package com.backend.backApp.service;

import com.backend.backApp.exceptions.AccountNotFoundException;
import com.backend.backApp.models.Account;
import com.backend.backApp.models.Friendship;
import com.backend.backApp.repository.AccountRepository;
import com.backend.backApp.repository.FriendshipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddAndDeleteFriendService {

    private final AccountRepository accountRepository;
    private final FriendshipRepository friendshipRepository;

    public AddAndDeleteFriendService(AccountRepository accountRepository, FriendshipRepository friendshipRepository) {
        this.accountRepository = accountRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public ResponseEntity<String> addFriendByEmail(String email1, String email2) {
        Account account1 = accountRepository.findByEmail(email1);
        if (account1 == null) {
            throw new AccountNotFoundException();
        }
        Account account2 = accountRepository.findByEmail(email2);
        if (account2 == null) {
            throw new AccountNotFoundException();
        }
        if (friendshipRepository.existsByAccount1AndAccount2(account1, account2) || friendshipRepository.existsByAccount1AndAccount2(account2, account1)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("They are already friends.");
        }

        if(account1.getId() == account2.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot be friend with yourself.");
        }

        Friendship friendship = new Friendship(account1, account2);
        friendshipRepository.save(friendship);

        return ResponseEntity.status(HttpStatus.CREATED).body("Friend added successfully.");
    }

    public ResponseEntity<String> removeFriendByEmail(String email1, String email2) {
        Account account1 = accountRepository.findByEmail(email1);
        if (account1 == null) {
            throw new AccountNotFoundException();
        }
        Account account2 = accountRepository.findByEmail(email2);
        if (account2 == null) {
            throw new AccountNotFoundException();
        }

        // Find the friendship in both directions (account1 -> account2 or account2 -> account1)
        Friendship friendship = friendshipRepository.findByAccount1AndAccount2(account1, account2);
        if (friendship == null) {
            friendship = friendshipRepository.findByAccount1AndAccount2(account2, account1);
        }

        if (friendship == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("They are not friends.");
        }

        friendshipRepository.delete(friendship);

        return ResponseEntity.status(HttpStatus.OK).body("Friend removed successfully.");
    }








}
