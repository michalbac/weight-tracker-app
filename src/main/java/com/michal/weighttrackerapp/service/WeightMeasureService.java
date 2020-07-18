package com.michal.weighttrackerapp.service;

import com.michal.weighttrackerapp.domain.User;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.WeightMeasureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WeightMeasureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeightMeasureService.class);

    @Autowired
    WeightMeasureRepository weightMeasureRepository;

    public List<WeightMeasure> getAllMeasuresForUser (User user){
        return weightMeasureRepository.findAllByUser(user);
    }

    public WeightMeasure getMeasure (int id){
        Optional<WeightMeasure> weightMeasure = weightMeasureRepository.findById(id);
        if (weightMeasure.isPresent()){
            return weightMeasure.get();
        }
        LOGGER.error("Measure with id: " + id + "not found");
        return null;
    }

    public WeightMeasure addMeasure (WeightMeasure weightMeasure){
        return weightMeasureRepository.save(weightMeasure);
    }

    public void deleteMeasure(final int id){
        weightMeasureRepository.deleteById(id);
    }

    public WeightMeasure updateMeasure(WeightMeasure weightMeasure){
        return weightMeasureRepository.save(weightMeasure);
    }
}
