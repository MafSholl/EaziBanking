package com.nibss.eazibank.services;

import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.respond.RegisterAccountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServicesImplTest {
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountCanBeCreatedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                                    "01-01-1991","08101234568", "Ayoola", "Afolabi");
//        request.setFirstName("Adeola");
//        request.setLastName("Ololade");
//        request.setPhoneNumber("08101234568");
//        request.setDOB("01-01-1991");
//        request.setMothersMaidenName("Ayoola");

        accountServices.createAccount(request);
        assertEquals(1, accountRepository.count());
    }

    @Test
    public void accountBalanceCanBeCredited(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                                    "01-01-1991","08101234568", "Ayoola", "Afolabi");
        RegisterAccountResponse savedAccount = accountServices.createAccount(request);

        CreditAccountRequest creditRequest = new CreditAccountRequest();
        accountServices.creditAccount(creditRequest);

        assertEquals(1, accountRepository.count());
    }
}