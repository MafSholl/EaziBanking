package com.eazibank.remabank;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@ComponentScan(basePackages = "com.eazibanking.remabank.**")
@EnableMongoRepositories
public class RemaBankModuleTestConfig {
}

