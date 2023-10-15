package com.example.project.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public void sendMail(SimpleMailMessage email);

    public void sendConfirm(String to,String email);
    public void sendNotification(String to, String link,String passs) ;
}
