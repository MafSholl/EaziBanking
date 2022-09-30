package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.AccountBalanceRequest;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.AccountDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServicesImplTest {
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountCanBeCreatedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");

        accountServices.createAccount(request);
        accountRepository.findByFirstName("Adeola").get();
        assertEquals(1, accountRepository.count());
    }

    @Test
    public void accountCreatedIsSavedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");

        RegisterAccountResponse response = accountServices.createAccount(request);
        Account accountCreated = accountRepository.findByFirstName("Adeola").get();

        assertEquals(accountCreated.getFirstName(), response.getFirstName());
        assertEquals(accountCreated.getLastName(), response.getLastName());
        assertEquals(accountCreated.getAccountNumber(), response.getAccountNumber());
        assertEquals(accountCreated.getBankVerificationNumber(), response.getBankVerificationNumber());
//        assertEquals(accountCreated.getDateCreation(), response.getAccountCreationDate());
    }

    @Test
    public void multipleAccountsCanBeCreatedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse account1 = accountServices.createAccount(request);
        assertEquals(1, accountRepository.count());
        assertEquals(account1.getAccountNumber(), accountRepository.findByAccountNumber(account1.getAccountNumber()).get().getAccountNumber());

        RegisterAccountRequest request1 = new RegisterAccountRequest("Kadijhat", "Osuolale",
                                        "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse account2 = accountServices.createAccount(request1);
        assertEquals(account2.getAccountNumber(), accountRepository.findByAccountNumber(account2.getAccountNumber()).get().getAccountNumber());;
    }

    @Test
    public void accountBalanceCanBeCreditedTest(){
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(request);

        CreditAccountRequest creditRequest = new CreditAccountRequest(createdAccount.getAccountNumber(), BigInteger.valueOf(1000));
        CreditAccountResponse creditResponse = accountServices.creditAccount(creditRequest);

        Account creditedAccount = accountRepository.findByFirstName("Adeola").get();

        assertEquals(new BigInteger("1000"), creditedAccount.getBalance());
        assertEquals(new BigInteger("1000"), creditResponse.getBalance());
    }

    @Test
    public void accountBalanceCanBeDebitedTest() {
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(request);

        CreditAccountRequest creditRequest = new CreditAccountRequest(createdAccount.getAccountNumber(), BigInteger.valueOf(1000));
        accountServices.creditAccount(creditRequest);

        DebitAccountRequest debitRequest = new DebitAccountRequest(createdAccount.getAccountNumber(), BigInteger.valueOf(500));
        DebitAccountResponse debitResponse = accountServices.debitAccount(debitRequest);

        Account debitedAccount = accountRepository.findByFirstName("Adeola").get();

        assertEquals(new BigInteger("500"), debitedAccount.getBalance());
        assertEquals(new BigInteger("500"), debitResponse.getDebitedAmount());
        assertEquals(new BigInteger("500"), debitResponse.getBalance());
    }

    @Test
    public void accountBalanceCanBeQueriedTest() {
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(request);

        CreditAccountRequest creditRequest = new CreditAccountRequest(createdAccount.getAccountNumber(), BigInteger.valueOf(1000));
        accountServices.creditAccount(creditRequest);

        AccountBalanceRequest checkBalanceRequest = new AccountBalanceRequest(createdAccount.getAccountNumber());
        AccountBalanceResponse checkBalanceResponse = accountServices.getBalance(checkBalanceRequest);

        assertEquals(new BigInteger("1000"), checkBalanceResponse.getBalance());
    }

    @Test
    public void incorrectAccountNumberThrowsException_WhenUsedInACreditTransactionTest() {
        RegisterAccountRequest request = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(request);

        CreditAccountRequest creditRequest = new CreditAccountRequest("1234567890", BigInteger.valueOf(1000));
        assertThrows(AccountDoesNotExistException.class, ()-> accountServices.creditAccount(creditRequest));
    }

    @Test
    public void incorrectAccountNumberThrowsException_WhenUsedInADebitTransactionTest() {
        RegisterAccountRequest registerRequest = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(registerRequest);

        DebitAccountRequest debitRequest = new DebitAccountRequest("1234567890", BigInteger.valueOf(1000));
        assertThrows(AccountDoesNotExistException.class, ()-> accountServices.debitAccount(debitRequest));
    }

    //Edge cases Tests
    //test to prevent duplicate accountNumbers to be generated

    @Test
    public void duplicateAccountWithSameAccountNumbers_IsPreventedTest(){
        RegisterAccountRequest registerRequest = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount = accountServices.createAccount(registerRequest);

        RegisterAccountRequest registerRequest1 = new RegisterAccountRequest("Olutola", "Osuolale",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        RegisterAccountResponse createdAccount1 = accountServices.createAccount(registerRequest1);

        assertEquals(2, accountRepository.count());
        Optional<Account> repoAccount = accountRepository.findByAccountNumber(createdAccount.getAccountNumber());
        assertTrue(repoAccount.isPresent());
        Optional<Account> repoAccount1 = accountRepository.findByFirstName("Olutola");
        assertTrue(repoAccount1.isPresent());
        assertNotEquals(repoAccount.get().getAccountNumber(), repoAccount1.get().getAccountNumber());
    }


}