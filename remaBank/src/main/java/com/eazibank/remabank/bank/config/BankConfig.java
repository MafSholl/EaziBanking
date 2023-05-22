package com.eazibank.remabank.bank.config;

import com.eazibank.remabank.bank.models.Bank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BankConfig {

    @Bean
    @Scope(value = "prototype")
    public Bank bank() {
            return new Bank();
    }
}
