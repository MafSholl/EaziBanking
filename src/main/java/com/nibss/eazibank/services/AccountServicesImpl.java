package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.AccountBalanceRequest;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.AccountDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

import static com.nibss.eazibank.data.models.enums.AccountType.*;

@Service
public class AccountServicesImpl implements AccountServices {
    @Autowired
    private AccountRepository accountRepository;
    private int accountNumber = 100_000_000;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public RegisterAccountResponse createAccount(RegisterAccountRequest request) {
//        Account account = new Account();
        Account account = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .accountNumber(accountNumberGenerator())
                .bankVerificationNumber(NibssInterfaceImpl.bvnGenerator())
                .build();
        if(request.getAccountType().equalsIgnoreCase("savings")) {
            account.setAccountType(SAVINGS);
        } else if(request.getAccountType().equalsIgnoreCase("current")) {
            account.setAccountType(CURRENT);
        } else if(request.getAccountType().equalsIgnoreCase("Domiciliary")) {
            account.setAccountType(DOMICILIARY);
        }

        Account createdAccount = accountRepository.save(account);

        RegisterAccountResponse response = modelMapper.map(createdAccount, RegisterAccountResponse.class);
        return response;
    }

    private String accountNumberGenerator() {
        StringBuilder generatedAccountNumber = new StringBuilder("0");
        generatedAccountNumber.append(this.accountNumber++);
        generatedAccountNumber = accountNumberValidator(generatedAccountNumber);
        return generatedAccountNumber.toString();
    }

    private StringBuilder accountNumberValidator(StringBuilder generatedAccountNumber) {
        if(accountRepository.findByAccountNumber(generatedAccountNumber.toString()).isPresent())
            generatedAccountNumber = new StringBuilder(accountNumberGenerator());
//        accountNumberValidator(generatedAccountNumber);
        return generatedAccountNumber;
    }

    @Override
    public CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) {
        Account account = new Account();
        account.setBalance(creditRequest.getAmount());
        account.setAccountNumber(creditRequest.getAccountNumber());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        BigInteger balance = accountInDatabase.get().getBalance();
        accountInDatabase.get().setBalance(balance.add(creditRequest.getAmount()));

        Account creditedAccount = accountRepository.save(accountInDatabase.get());
        CreditAccountResponse creditResponse = new CreditAccountResponse();
        if (creditedAccount.getBalance().compareTo(balance) > 0) {
            creditResponse.setSuccessful(true);
            creditResponse.setAccountNumber(creditedAccount.getAccountNumber());
            creditResponse.setBalance(creditedAccount.getBalance());
            creditResponse.setFirstName(creditedAccount.getFirstName());
            creditResponse.setLastName(creditedAccount.getLastName());
            creditResponse.setBankVerificationNumber(creditedAccount.getBankVerificationNumber());
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

    @Override
    public Optional<Account> findAccount(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        return account;
    }
}
