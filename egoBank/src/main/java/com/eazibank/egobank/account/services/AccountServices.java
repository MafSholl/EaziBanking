package com.eazibank.egobank.account.services;

import com.eazibank.egobank.account.models.Account;
import com.eazibank.egobank.exception.exceptions.AccountDoesNotExistException;
import com.eazibank.egobank.account.dto.request.DebitAccountRequest;
import com.eazibank.egobank.account.dto.response.AccountBalanceResponse;
import com.eazibank.egobank.account.dto.response.CreditAccountResponse;
import com.eazibank.egobank.account.dto.response.DebitAccountResponse;
import com.eazibank.egobank.account.dto.response.RegisterAccountResponse;
import com.eazibank.egobank.account.dto.request.AccountBalanceRequest;
import com.eazibank.egobank.account.dto.request.CreditAccountRequest;
import com.eazibank.egobank.account.dto.request.RegisterAccountRequest;

import java.util.Optional;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) throws AccountDoesNotExistException;

    DebitAccountResponse debitAccount(DebitAccountRequest debitRequest);

    AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest);

    Optional<Account> findAccount(String accountNumber);
}
