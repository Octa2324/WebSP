package com.backend.backApp.repository;

import com.backend.backApp.models.Account;
import com.backend.backApp.models.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship,Integer> {

    List<Friendship> findByAccount1(Account account);

    List<Friendship> findByAccount2(Account account);

    boolean existsByAccount1AndAccount2(Account account1, Account account2);

    Friendship findByAccount1AndAccount2(Account account1, Account account2);
}
