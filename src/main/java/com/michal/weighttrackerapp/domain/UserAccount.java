package com.michal.weighttrackerapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "user_accounts")
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name = "user_accounts_seq", sequenceName = "user_accounts_seq", allocationSize = 1)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String userName;
    private String email;
    private String password;
    private boolean enabled = true;

}
