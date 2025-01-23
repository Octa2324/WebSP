package com.backend.backApp.models;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDTO {
    private Integer id;
    private String email;
    //private String password;
    private String firstName;
    private String lastName;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.email = account.getEmail();
        //this.password = account.getPassword();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
    }

}
