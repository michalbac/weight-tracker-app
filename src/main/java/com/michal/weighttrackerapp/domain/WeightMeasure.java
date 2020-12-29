package com.michal.weighttrackerapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class WeightMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double weight;
    @NotNull(message = "Date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfMeasure;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;
}
