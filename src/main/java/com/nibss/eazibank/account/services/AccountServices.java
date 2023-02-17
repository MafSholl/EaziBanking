package com.nibss.eazibank.account.services;

import com.nibss.eazibank.account.dto.request.DebitAccountRequest;
import com.nibss.eazibank.account.dto.response.AccountBalanceResponse;
import com.nibss.eazibank.account.dto.response.CreditAccountResponse;
import com.nibss.eazibank.account.dto.response.DebitAccountResponse;
import com.nibss.eazibank.account.dto.response.RegisterAccountResponse;
import com.nibss.eazibank.account.models.Account;
import com.nibss.eazibank.account.dto.request.AccountBalanceRequest;
import com.nibss.eazibank.account.dto.request.CreditAccountRequest;
import com.nibss.eazibank.account.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.exception.exceptions.AccountDoesNotExistException;

import java.util.Optional;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) throws AccountDoesNotExistException;

    DebitAccountResponse debitAccount(DebitAccountRequest debitRequest);

    AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest);

    Optional<Account> findAccount(String accountNumber);
}
