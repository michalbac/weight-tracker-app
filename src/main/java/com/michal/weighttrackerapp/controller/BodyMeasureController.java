package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/body")
public class BodyMeasureController {
    @Autowired
    BodyMeasureService bodyMeasureService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/new")
    public String create(Model model){
        BodyMeasure bodyMeasure = new BodyMeasure();
        model.addAttribute("body", bodyMeasure);
        return"body/new-body-measure";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("body") @Valid BodyMeasure bodyMeasure, Authentication authentication, Errors errors){
        String name = authentication.getName();
        String username = name;
        UserAccount userAccount = userRepository.findByUserName(username);
        if(errors.hasErrors()){
            return "body/new-body-measure";
        }
        bodyMeasure.setUser(userAccount);
        bodyMeasureService.addMeasure(bodyMeasure);
        return "redirect:/body";
    }
    @GetMapping
    public String getMeasures(Model model, Authentication authentication){
        String userName = authentication.getName();
        Long id = userRepository.findByUserName(userName).getId();
        List<BodyMeasure> bodyMeasures = bodyMeasureService.getAllUserBodyMeasures(id);
        model.addAttribute("measures", bodyMeasures);
        return "body/all-measures";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable long id){
        bodyMeasureService.deleteMeasure(id);
        return "redirect:/body";
    }
}
