package com.michal.weighttrackerapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    WeightMeasureService weightMeasureService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
        public String displayHome(Model model, Authentication authentication) throws JsonProcessingException {
        if(authentication!=null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Long id = userRepository.findByUserName(username).getId();
            List<WeightMeasure> measureList = weightMeasureService.getAllUserWeights(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonWeightString = objectMapper.writeValueAsString(measureList);
            model.addAttribute("measureList", jsonWeightString);
        }
            return"main/home";
        }

}
