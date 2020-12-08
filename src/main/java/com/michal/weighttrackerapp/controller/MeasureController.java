package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MeasureController {
    @Autowired
    WeightMeasureService weightMeasureService;

    @PostMapping()
    public WeightMeasure addWeightMeasure (@RequestBody WeightMeasure weightMeasure){
        return weightMeasureService.addMeasure(weightMeasure);
    }

}
