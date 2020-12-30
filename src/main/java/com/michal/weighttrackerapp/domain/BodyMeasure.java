package com.michal.weighttrackerapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class BodyMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measure_seq")
    @SequenceGenerator(name = "body_measure_seq", sequenceName = "body_measure_seq", allocationSize = 1)
    private long id;
    private double rightBicep;
    private double leftBicep;
    private double waist;
    private double chest;
    private double leftThigh;
    private double rightThigh;
    @NotNull(message = "Date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfMeasure;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserAccount user;
}
