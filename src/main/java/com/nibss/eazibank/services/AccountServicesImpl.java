package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.repositories.AccountRepository;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServicesImpl implements AccountServices {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void createAccount(RegisterAccountRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setAccountNumber("0113456789");
        account.setPhoneNumber(request.getPhoneNumber());

        accountRepository.save(account);
    }
}
