package com.michal.weighttrackerapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name = "user_accounts_seq", sequenceName = "user_accounts_seq", allocationSize = 1)
    private long id;

    @Column(name = "username")
    @UniqueElements
    private String userName;
    private String email;
    private String password;
    private boolean enabled = true;
    @JsonManagedReference
    @OneToMany(targetEntity = BodyMeasure.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BodyMeasure> bodyMeasures;
    @OneToMany(targetEntity = WeightMeasure.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private  List<WeightMeasure> weightMeasures;

}
