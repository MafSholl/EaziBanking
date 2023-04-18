package com.eazibanking.nibss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@ComponentScan(basePackages = "com.eazibank.nibss.**")
public class NibssModuleTestConfig {
}
