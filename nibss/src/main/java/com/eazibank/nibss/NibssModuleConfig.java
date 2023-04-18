package com.eazibank.nibss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.eazibank.**.**")
@EnableMongoRepositories
@Configuration
public class NibssModuleConfig {
}
