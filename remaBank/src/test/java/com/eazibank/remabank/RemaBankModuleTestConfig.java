package com.eazibank.remabank;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.eazibanking.remabank.**")
@EnableMongoRepositories
public class RemaBankModuleTestConfig {
}

