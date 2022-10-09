package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.*;
import com.nibss.eazibank.dto.response.*;

public interface CustomerServices {

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDepositResponse deposit(DepositRequest depositRequest);

    CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest);

    CustomerTransferResponse transfer(CustomerTransferRequest transferRequest);

    CustomerLoginResponse login(CustomerLoginRequest customerLoginrequest);

    CreatePasswordResponse setCustomerPassword(CreatePasswordRequest createPasswordRequest);

    ViewProfileResponse viewCustomerProfile(ViewProfileRequest viewProfileRequest);

    ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest viewTransactionHistoryRequest);
}
