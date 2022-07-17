package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.CreditAccountRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.respond.RegisterAccountResponse;

public interface AccountServices {

    RegisterAccountResponse createAccount(RegisterAccountRequest request);

    void creditAccount(CreditAccountRequest creditRequest);
}
