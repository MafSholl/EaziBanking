package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;

public interface CustomerServices {

    CustomerDepositResponse deposit(CustomerDepositRequest depositRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
}
