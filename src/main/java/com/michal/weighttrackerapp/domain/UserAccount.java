package com.michal.weighttrackerapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user_accounts")
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name = "user_accounts_seq", sequenceName = "user_accounts_seq", allocationSize = 1)
    private long userId;

    @Column(name = "username")
    private String userName;
    private String email;
    private String password;
    private boolean enabled = true;
    @OneToMany(targetEntity = BodyMeasure.class, mappedBy = "user")
    private List<BodyMeasure> bodyMeasures;
    @OneToMany(targetEntity = WeightMeasure.class, mappedBy = "user")
    private  List<WeightMeasure> weightMeasures;

}
