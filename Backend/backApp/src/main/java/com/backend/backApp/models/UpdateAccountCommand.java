package com.backend.backApp.models;

import lombok.Getter;

@Getter
public class UpdateAccountCommand {
    private Integer id;
    private Account account;

    public UpdateAccountCommand(Integer id, Account account){
        this.id = id;
        this.account = account;
    }
}
