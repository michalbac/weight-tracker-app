package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.Mail;
import com.michal.weighttrackerapp.domain.UserAccount;
import com.michal.weighttrackerapp.repository.UserRepository;
import com.michal.weighttrackerapp.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpleEmailService simpleEmailService;


    @GetMapping(value = "/register")
    public String registerUser(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @PostMapping(value = "/register/save")
    public String saveUser (Model model, UserAccount userAccount){
        userAccount.setPassword((bCryptPasswordEncoder.encode(userAccount.getPassword())));
        userRepository.save(userAccount);
        simpleEmailService.sendUserRegistration(new Mail(userAccount.getEmail(),simpleEmailService.MAIL_SUBJECT, simpleEmailService.MAIL_MESSAGE, null ));
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return"login";
    }
}
