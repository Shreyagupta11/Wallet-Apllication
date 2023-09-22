package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String content) {

        log.info("Process is started");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shreya11081999@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        log.info("Email sent successfully");
        System.out.println("Mail sent");
    }


}
