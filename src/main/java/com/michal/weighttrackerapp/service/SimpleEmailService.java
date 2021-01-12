package com.michal.weighttrackerapp.service;

import com.michal.weighttrackerapp.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Service
public class SimpleEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    public static final String MAIL_SUBJECT = "Welcome in WeightTracker";
    public static final String MAIL_MESSAGE = "Thank you for registration in WeighTracker Application";


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void sendAdminMail(final Mail mail){
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createAdminMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (Exception e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }

    }

    public void sendUserRegistration(final Mail mail){
        LOGGER.info("Starting email preparation for user: " + mail.getMailTo() + "...");
        try {
            javaMailSender.send(createUserRegistrationMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (Exception e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }

    }

    private MimeMessagePreparator createUserRegistrationMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.userRegistrationMail(mail.getMessage()), true);
        };
    }

    private MimeMessagePreparator createAdminMimeMessage(final Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildAdminMail(mail.getMessage()), true);
        };
    }
}
