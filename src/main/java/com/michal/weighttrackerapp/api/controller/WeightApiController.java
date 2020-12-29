package com.michal.weighttrackerapp.api.controller;

import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/weights")
public class WeightApiController {

    @Autowired
    WeightMeasureService weightMeasureService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<WeightMeasure> getWeights(){
        return weightMeasureService.getAllWeights();
    }

    @GetMapping("/{id}")
    public List<WeightMeasure> getWeightsByUser(@PathVariable long id){
        Optional<UserAccount> userAccount = userRepository.findById(id);
        if(userAccount.isPresent()){
            return userAccount.get().getWeightMeasures();
        } else {
            return new ArrayList<>();
        }

    }
}
