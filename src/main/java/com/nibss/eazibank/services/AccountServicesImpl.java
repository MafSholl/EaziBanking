package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.respond.RegisterAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class AccountServicesImpl implements AccountServices {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public RegisterAccountResponse createAccount(RegisterAccountRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setAccountNumber("0113456789");
        account.setPhoneNumber(request.getPhoneNumber());

        Account savedAccount = accountRepository.save(account);

        RegisterAccountResponse response = new RegisterAccountResponse();
        response.setFirstName(savedAccount.getFirstName());
        response.setLastName(savedAccount.getLastName());
        response.setAccountNumber(savedAccount.getAccountNumber());
        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy, hh:mm, a").format(savedAccount.getAccountCreationDate()));
        return response;
    }

    @Override
    public void creditAccount(CreditAccountRequest creditRequest) {
        Account account = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        account.setBalance(creditRequest.getAmount());
        accountRepository.save(account);
    }
}
