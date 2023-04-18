package com.eazibank.remabank.account.services;

import com.eazibank.remabank.account.models.Account;
import com.eazibank.remabank.exception.exceptions.AccountDoesNotExistException;
import com.eazibank.remabank.account.dto.request.DebitAccountRequest;
import com.eazibank.remabank.account.dto.response.AccountBalanceResponse;
import com.eazibank.remabank.account.dto.response.CreditAccountResponse;
import com.eazibank.remabank.account.dto.response.DebitAccountResponse;
import com.eazibank.remabank.account.dto.response.RegisterAccountResponse;
import com.eazibank.remabank.account.dto.request.AccountBalanceRequest;
import com.eazibank.remabank.account.dto.request.CreditAccountRequest;
import com.eazibank.remabank.account.dto.request.RegisterAccountRequest;

import java.util.Optional;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) throws AccountDoesNotExistException;

    DebitAccountResponse debitAccount(DebitAccountRequest debitRequest);

    AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest);

    Optional<Account> findAccount(String accountNumber);
}
