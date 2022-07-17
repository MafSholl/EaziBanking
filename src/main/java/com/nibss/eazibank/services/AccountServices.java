package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.RegisterAccountRequest;

public interface AccountServices {

    void createAccount(RegisterAccountRequest request);
}
