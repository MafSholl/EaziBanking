package com.eazibank.remabank.account.repository;

import com.eazibank.remabank.RemaBankModuleConfig;
import com.eazibank.remabank.account.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ContextConfiguration(classes = RemaBankModuleConfig.class)
@ActiveProfiles("dev")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountRepositoryWorksTest(){
        Account account = new Account();
        accountRepository.save(account);
        assertEquals(1, accountRepository.count());
    }


}