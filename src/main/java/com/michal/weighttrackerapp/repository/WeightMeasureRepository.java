package com.michal.weighttrackerapp.repository;

import com.michal.weighttrackerapp.domain.User;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightMeasureRepository extends JpaRepository<WeightMeasure, Integer> {

    List<WeightMeasure> findAllByUser(User user);
}
