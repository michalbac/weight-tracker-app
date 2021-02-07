package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.BodyMeasure;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.domain.WeightMeasure;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WeightMeasureService weightMeasureService;

    @Autowired
    BodyMeasureService bodyMeasureService;

    @GetMapping
    public String home(Model model){
        List<UserAccount> users = userRepository.findAll();
        model.addAttribute("usersCount", users.size());
        List<BodyMeasure> bodyMeasures = bodyMeasureService.getAllMeasures();
        model.addAttribute("bodySize", bodyMeasures.size());
        List<WeightMeasure> weightMeasures = weightMeasureService.getAllWeights();
        model.addAttribute("weightSize", weightMeasures.size());
        return"admin/admin-home";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserAccount> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }


}
