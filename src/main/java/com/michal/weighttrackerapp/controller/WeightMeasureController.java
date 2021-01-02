package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/weights")
public class WeightMeasureController {
    @Autowired
    WeightMeasureService weightMeasureService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/new")
    public String displayForm(Model model){
        WeightMeasure weightMeasure = new WeightMeasure();
        model.addAttribute("weight", weightMeasure);
        return "weight/new-weight";
    }

    @PostMapping("/save")
    public String create (@ModelAttribute("weight") @Valid WeightMeasure weightMeasure, Errors errors, Model model, Authentication authentication){
        String name = authentication.getName();
        String username = name;
        UserAccount userAccount = userRepository.findByUserName(username);
        if(errors.hasErrors()){
            return "weight/new-weight";
        }
        weightMeasure.setUser(userAccount);
        weightMeasureService.addMeasure(weightMeasure);
        return "redirect:/weights";
    }

    @GetMapping
    public String getMeasures(Model model, Authentication authentication){
        String username = authentication.getName();
        Long id = userRepository.findByUserName(username).getId();
        List<WeightMeasure> measureList = weightMeasureService.getAllUserWeights(id);
        model.addAttribute("measureList", measureList);
        return"weight/weights_page";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam long id){
        weightMeasureService.deleteMeasure(id);
        return "redirect:/weights";
    }

}
