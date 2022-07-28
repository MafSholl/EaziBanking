package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.*;
import com.nibss.eazibank.exception.AccountDoesNotExistException;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) throws AccountDoesNotExistException;

    DebitAccountResponse debitAccount(DebitAccountRequest debitRequest);

    AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest);
}
