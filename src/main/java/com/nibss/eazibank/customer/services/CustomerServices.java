package com.nibss.eazibank.customer.services;

import com.nibss.eazibank.customer.dto.request.*;
import com.nibss.eazibank.customer.dto.response.*;
import com.nibss.eazibank.staff.controller.requests.DepositRequest;
import com.nibss.eazibank.transaction.dto.response.ViewTransactionHistoryResponse;

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
