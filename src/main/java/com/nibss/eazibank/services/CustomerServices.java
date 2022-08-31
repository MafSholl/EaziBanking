package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.*;
import com.nibss.eazibank.dto.response.*;

import java.math.BigInteger;

public interface CustomerServices {

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDepositResponse deposit(CustomerDepositRequest depositRequest);

    CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest);

    CustomerTransferResponse transfer(CustomerTransferRequest transferRequest);

    CustomerLoginResponse login(CustomerLoginRequest customerLoginrequest);

    CreatePasswordResponse setCustomerPassword(CreatePasswordRequest createPasswordRequest);

    ViewProfileResponse viewCustomerProfile(ViewProfileRequest viewProfileRequest);
}
