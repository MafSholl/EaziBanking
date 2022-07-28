package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.AccountDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AccountServicesImpl implements AccountServices {

    @Autowired
    private AccountRepository accountRepository;
    private int accountNumber = 100_000_000;
    @Override
    public RegisterAccountResponse createAccount(RegisterAccountRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setPhoneNumber(request.getPhoneNumber());

        account.setAccountNumber(accountNumberGenerator());

        Account createdAccount = accountRepository.save(account);

        RegisterAccountResponse response = new RegisterAccountResponse();
        response.setFirstName(createdAccount.getFirstName());
        response.setLastName(createdAccount.getLastName());
        response.setAccountNumber(createdAccount.getAccountNumber());
        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(createdAccount.getAccountCreationDate()));
        return response;
    }

    private String accountNumberGenerator() {
        StringBuilder generatedAccountNumber = new StringBuilder("0");
        generatedAccountNumber.append(this.accountNumber++);
        System.out.println(this.accountNumber);

        if(accountRepository.findByAccountNumber(generatedAccountNumber.toString()).isPresent())
            generatedAccountNumber = new StringBuilder(accountNumberGenerator());
        return generatedAccountNumber.toString();
    }

    private StringBuilder accountNumberValidator(StringBuilder accountNumber) {
        if(accountRepository.findByAccountNumber(accountNumber.toString()).isPresent())
            accountNumber = new StringBuilder(accountNumberGenerator());
        return accountNumber;
    }

    @Override
    public CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) {
        Account account = new Account();
        account.setBalance(creditRequest.getAmount());
        account.setAccountNumber(creditRequest.getAccountNumber());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        BigInteger balance = accountInDatabase.get().getBalance();
        accountInDatabase.get().setBalance(accountInDatabase.get().getBalance().add(creditRequest.getAmount()));

        Account creditedAccount = accountRepository.save(accountInDatabase.get());
        CreditAccountResponse creditResponse = new CreditAccountResponse();
        if (creditedAccount.getBalance().compareTo(balance) > 0) {
            creditResponse.setSuccessful(true);
            creditResponse.setAccountNumber(creditedAccount.getAccountNumber());
            creditResponse.setBalance(creditedAccount.getBalance());
            creditResponse.setFirstName(creditedAccount.getFirstName());
            creditResponse.setLastName(creditedAccount.getLastName());
        }
        return creditResponse;
    }

    @Override
    public DebitAccountResponse debitAccount(DebitAccountRequest debitRequest) {
        Account account = new Account();
        account.setAccountNumber(debitRequest.getAccountNumber());
        account.setBalance(debitRequest.getAmount());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(account.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        BigInteger previousBalance = accountInDatabase.get().getBalance();
        accountInDatabase.get().setBalance(accountInDatabase.get().getBalance().subtract(account.getBalance()));
        Account debitedAccount = accountRepository.save(accountInDatabase.get());

        DebitAccountResponse response = new DebitAccountResponse();
        if(previousBalance.subtract(debitedAccount.getBalance()).equals(debitRequest.getAmount())) {
            response.setSuccessful(true);
            response.setAccountNumber(debitedAccount.getAccountNumber());
            response.setFirstName(debitedAccount.getFirstName());
            response.setLastName(debitedAccount.getLastName());
            response.setDebitedAmount(debitRequest.getAmount());
            response.setBalance(debitedAccount.getBalance());
        }

        return response;
    }

    @Override
    public AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest) {
        Account account = new Account();
        account.setAccountNumber(checkBalanceRequest.getAccountNumber());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(account.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        AccountBalanceResponse response = new AccountBalanceResponse();
        response.setBalance(accountInDatabase.get().getBalance());
        response.setFirstName(accountInDatabase.get().getFirstName());
        response.setLastName(accountInDatabase.get().getLastName());
        return response;
    }
}
