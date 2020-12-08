package com.michal.weighttrackerapp.repository;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyMeasureRepository extends JpaRepository<BodyMeasure, Long> {
}
