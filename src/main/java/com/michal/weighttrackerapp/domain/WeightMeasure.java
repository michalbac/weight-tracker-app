package com.michal.weighttrackerapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class WeightMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double weight;
    private Date dateOfMeasure;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
