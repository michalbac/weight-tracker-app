package com.michal.weighttrackerapp.api.controller;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/body")
public class BodyApiController {
    @Autowired
    BodyMeasureService bodyMeasureService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<BodyMeasure> getAllMeasures(){
        return bodyMeasureService.getAllMeasures();
    }

    @GetMapping("/{id}")
    public List<BodyMeasure> getUserMeasures(@PathVariable long id){
        return bodyMeasureService.getAllUserBodyMeasures(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        bodyMeasureService.deleteMeasure(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BodyMeasure create (@RequestBody BodyMeasure bodyMeasure){
        return bodyMeasureService.addMeasure(bodyMeasure);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BodyMeasure update(@RequestBody BodyMeasure bodyMeasure){
        return bodyMeasureService.addMeasure(bodyMeasure);
    }
}
