package com.michal.weighttrackerapp.service;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.BodyMeasureRepository;
import com.michal.weighttrackerapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BodyMeasureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BodyMeasureService.class);

    @Autowired
    BodyMeasureRepository bodyMeasureRepository;

    @Autowired
    UserRepository userRepository;

    public BodyMeasure getMeasure(long id){
        Optional<BodyMeasure> bodyMeasure = bodyMeasureRepository.findById(id);
        if(bodyMeasure.isPresent()){
            return bodyMeasure.get();
        }
        LOGGER.error("Measure with id: " + id + "not found");
        return null;
    }

    public List<BodyMeasure> getAllMeasures(){
        return bodyMeasureRepository.findAll();
    }

    public void deleteMeasure(long id){
        bodyMeasureRepository.deleteById(id);
    }

    public BodyMeasure addMeasure(BodyMeasure bodyMeasure){
        return bodyMeasureRepository.save(bodyMeasure);
    }

    public List<BodyMeasure> getAllUserBodyMeasures(long id){
        Optional<UserAccount> userAccount = userRepository.findById(id);
        if(userAccount.isPresent()){
            return userAccount.get().getBodyMeasures();
        } else {
            return new ArrayList<>();
        }
    }


}
