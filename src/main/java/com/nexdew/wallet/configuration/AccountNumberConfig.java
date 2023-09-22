package com.nexdew.wallet.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
public class AccountNumberConfig {
    @Value("${initial.user.account.number}")
    private String initialAccountNumber;

    @Bean
    public String initialCustomerAccountNumber() {
        return initialAccountNumber;
    }

}
