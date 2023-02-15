package com.nibss.eazibank.data.repositories;

import com.nibss.eazibank.account.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
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