package com.michal.weighttrackerapp.api.controller;

import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return weightMeasureService.getAllUserWeights(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public WeightMeasure create (@RequestBody WeightMeasure weightMeasure){
        return weightMeasureService.addMeasure(weightMeasure);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public WeightMeasure update (@RequestBody WeightMeasure weightMeasure){
        return weightMeasureService.updateMeasure(weightMeasure);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        weightMeasureService.deleteMeasure(id);
    }

}
