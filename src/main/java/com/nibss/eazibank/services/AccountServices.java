package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.dto.request.AccountBalanceRequest;
import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.exceptions.AccountDoesNotExistException;

import java.util.Optional;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) throws AccountDoesNotExistException;

    DebitAccountResponse debitAccount(DebitAccountRequest debitRequest);

    AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest);

    Optional<Account> findAccount(String accountNumber);
}
