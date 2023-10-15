package com.example.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendMail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void sendConfirm(String to, String link) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setFrom("taixexedo@gmail.com");
            helper.setSubject("Confirm your email");
            helper.setText("To Confirm your account, click the link below: \n" + //
                    link);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
          
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    @Async
    public void sendNotification(String to, String link, String pass) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setFrom("taixexedo@gmail.com");
            helper.setSubject("Account create");
            helper.setText("Your account has been created by Admin\n" + //
                    "Your password is: " + pass + "\n" +
                    "Click here to login in your account \n" + //
                    "" + link);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

}
