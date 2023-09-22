package com.nexdew.wallet.configuration.mailConfig;

import com.nexdew.wallet.configuration.exceptionconfig.CustomException;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailScheduler {

        @Autowired
        private UserRepository userRepository;


        private final IEmailService emailService;

    @Scheduled(cron = "0 * * * * *")
        public void sendExpiryEmails() {
        log.info("Here is the Expiry Mail Scheduler");

            List<User> expiringCustomers = userRepository.findByExpireDateOrExpireDate(LocalDate.now(), LocalDate.now().plusDays(1));

            for (User customer : expiringCustomers) {
                log.info("inside loop");
                String emailContent = "Dear " + customer.getUsername() + ", your subscription is expiring soon.";
                emailService.sendEmail(customer.getEmail(), "Subscription Expiry", emailContent);
            }
        }


}
