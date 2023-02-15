package com.nibss.eazibank.bank.services;

import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;


public interface BankServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    boolean isNibssInterfaceAvailable();
}
