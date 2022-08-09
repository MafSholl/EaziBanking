package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.request.CustomerWithdrawalRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.CustomerWithdrawalResponse;

public interface CustomerServices {

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDepositResponse deposit(CustomerDepositRequest depositRequest);

    CustomerWithdrawalResponse withdraw(CustomerWithdrawalRequest withdrawalRequest);
}
