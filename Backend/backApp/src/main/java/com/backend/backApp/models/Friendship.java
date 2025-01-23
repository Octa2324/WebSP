package com.backend.backApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "friendships", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account1_id", "account2_id"})
})
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account1_id", nullable = false)
    private Account account1;

    @ManyToOne
    @JoinColumn(name = "account2_id", nullable = false)
    private Account account2;

    public Friendship(Account account1, Account account2) {
        this.account1 = account1;
        this.account2 = account2;
    }
}
