package com.nexdew.wallet.quartz;

import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class WelcomeEmail implements Job {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;



    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("execute method execution start");
        try {
            JobDataMap jobDataMap  = jobExecutionContext.getJobDetail().getJobDataMap();
            String username = String.valueOf(jobDataMap.getInt("username"));
            User customer = userRepository.findByUsername(username);
            sendSignupEmail(customer);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendSignupEmail(User customer) throws MessagingException {
        log.info("sendSignupEmail method execution start");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,false);

        helper.setTo(customer.getEmail().toLowerCase());
        helper.setSubject("Welcome to Wallet Application!");
        helper.setText("Hello " + customer.getUsername() + " "+customer.getAppUserRoles() +",\nThank you for sign up.");
        javaMailSender.send(message);
    }
}