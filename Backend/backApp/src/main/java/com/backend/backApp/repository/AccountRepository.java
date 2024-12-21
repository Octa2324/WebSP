package com.backend.backApp.repository;

import com.backend.backApp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByFirstNameContaining(String name);
    Account findByEmail(String email);
}
