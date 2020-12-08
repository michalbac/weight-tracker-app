package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public String registerUser(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String saveUser (Model model, UserAccount userAccount){
        userAccount.setPassword((bCryptPasswordEncoder.encode(userAccount.getPassword())));
        userRepository.save(userAccount);
        return "redirect:/";

    }
}
