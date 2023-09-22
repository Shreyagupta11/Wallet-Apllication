package com.nexdew.wallet.quartz;

import com.nexdew.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {

    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private final UserRepository userRepository;


    @Bean
    public WelcomeEmail emailJob() {
        return new WelcomeEmail(javaMailSender, userRepository);
    }



    // Method to create and configure the JobDetail
    public static JobDetail emailJobDetails(String username) {
        log.info("emailJobDetails method execution start");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("username", username);
        return JobBuilder.newJob(WelcomeEmail.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    // Method to create and configure the Trigger
    public static Trigger emailTrigger(JobDetail job, long timeDuration) {
        log.info("emailTrigger method execution start");
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(job.getKey().getName(), "email-trigger")
                .withDescription("Send Email Trigger")
                .startAt(DateBuilder.futureDate((int) timeDuration, DateBuilder.IntervalUnit.MINUTE))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}