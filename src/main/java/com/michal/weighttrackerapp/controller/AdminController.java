package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.BodyMeasureService;
import com.michal.weighttrackerapp.service.WeightMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public String getAllUsers(Model model){
        List<UserAccount> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }
}
