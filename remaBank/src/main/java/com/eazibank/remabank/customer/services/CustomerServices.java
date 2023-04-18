package com.eazibank.remabank.customer.services;

import com.eazibank.remabank.customer.dto.request.*;
import com.eazibank.remabank.customer.dto.response.*;
import com.eazibank.remabank.staff.controller.requests.DepositRequest;
import com.eazibank.remabank.transaction.dto.response.ViewTransactionHistoryResponse;

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
