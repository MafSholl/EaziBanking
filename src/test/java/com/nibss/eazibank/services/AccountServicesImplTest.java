package com.nibss.eazibank.services;

import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServicesImplTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountCanBeCreatedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                                    "01-01-1991","08101234568", "Ayoola", "");
//        request.setFirstName("Adeola");
//        request.setLastName("Ololade");
//        request.setPhoneNumber("08101234568");
//        request.setDOB("01-01-1991");
//        request.setMothersMaidenName("Ayoola");

        AccountServices accountService = new AccountServicesImpl();
        accountService.createAccount(request);

        assertEquals(1, accountRepository.count());

    }
}