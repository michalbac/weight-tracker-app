package com.michal.weighttrackerapp.service;

import com.michal.weighttrackerapp.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;


    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildAdminMail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("button", "Check admin page");
        return templateEngine.process("mail/admin-update-mail", context);
    }

    public String userRegistrationMail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("home_page_url", "http://localhost:8080/");
        context.setVariable("button", "Visit website");

        return templateEngine.process("mail/user-registration-mail", context);


    }

}
