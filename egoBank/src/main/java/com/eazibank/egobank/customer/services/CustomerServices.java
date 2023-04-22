package com.eazibank.egobank.customer.services;

import com.eazibank.egobank.customer.dto.request.*;
import com.eazibank.egobank.customer.dto.response.*;
import com.eazibank.egobank.staff.controller.requests.DepositRequest;
import com.eazibank.egobank.transaction.dto.response.ViewTransactionHistoryResponse;

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
