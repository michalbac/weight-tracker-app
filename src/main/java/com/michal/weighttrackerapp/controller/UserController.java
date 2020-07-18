package com.michal.weighttrackerapp.controller;

import com.michal.weighttrackerapp.domain.User;
import com.michal.weighttrackerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login (String login, String password){
        User user = userRepository.findByEmail(login);
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
