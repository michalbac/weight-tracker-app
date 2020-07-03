package com.michal.weighttrackerapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double height;
    private String sex;
    @OneToMany(mappedBy = "user")
    private List<WeightMeasure> weightMeasures;

    }
