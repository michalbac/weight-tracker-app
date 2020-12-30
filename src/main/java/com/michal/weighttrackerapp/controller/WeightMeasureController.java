package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/weights")
public class WeightMeasureController {
    @Autowired
    WeightMeasureService weightMeasureService;

    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public WeightMeasure addWeightMeasure (@RequestBody WeightMeasure weightMeasure){
        return weightMeasureService.addMeasure(weightMeasure);
    }

    @GetMapping
    public String getMeasures(Model model, Authentication authentication){
        String username = authentication.getName();
        Long id = userRepository.findByUsername(username).getId();
        List<WeightMeasure> measureList = weightMeasureService.getAllUserWeights(id);
        model.addAttribute("measureList", measureList);
        return"";

    }

}