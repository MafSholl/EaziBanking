package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CreateStaffResponse;
import org.springframework.stereotype.Service;


public interface BankServices {

    CreateStaffResponse createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    boolean isNibssInterfaceAvailable();
}
